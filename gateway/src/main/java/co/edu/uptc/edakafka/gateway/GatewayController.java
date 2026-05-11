package co.edu.uptc.edakafka.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class GatewayController {

    private final WebClient webClient;

    @Value("${services.order.url}")
    private String orderServiceUrl;

    @Value("${services.payment.url}")
    private String paymentServiceUrl;

    @Value("${services.shipping.url}")
    private String shippingServiceUrl;

    @Value("${services.inventory.url}")
    private String inventoryServiceUrl;

    public GatewayController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    // ── ORDERS bounded context ─────────────────────────────────────
    @RequestMapping(value = "/orders/**", method = {
            RequestMethod.GET, RequestMethod.POST,
            RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH
    })
    public Mono<ResponseEntity<byte[]>> proxyOrders(
            ServerWebExchange exchange,
            @RequestBody(required = false) Mono<byte[]> body) {
        return proxy(exchange, body, orderServiceUrl);
    }

    // ── PAYMENTS bounded context ───────────────────────────────────
    @RequestMapping(value = "/payments/**", method = {
            RequestMethod.GET, RequestMethod.POST,
            RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH
    })
    public Mono<ResponseEntity<byte[]>> proxyPayments(
            ServerWebExchange exchange,
            @RequestBody(required = false) Mono<byte[]> body) {
        return proxy(exchange, body, paymentServiceUrl);
    }

    // ── SHIPPING bounded context ───────────────────────────────────
    @RequestMapping(value = "/shipping/**", method = {
            RequestMethod.GET, RequestMethod.POST,
            RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH
    })
    public Mono<ResponseEntity<byte[]>> proxyShipping(
            ServerWebExchange exchange,
            @RequestBody(required = false) Mono<byte[]> body) {
        return proxy(exchange, body, shippingServiceUrl);
    }

    // ── INVENTORY bounded context ──────────────────────────────────
    @RequestMapping(value = "/inventory/**", method = {
            RequestMethod.GET, RequestMethod.POST,
            RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH
    })
    public Mono<ResponseEntity<byte[]>> proxyInventory(
            ServerWebExchange exchange,
            @RequestBody(required = false) Mono<byte[]> body) {
        return proxy(exchange, body, inventoryServiceUrl);
    }

    // ── Lógica de proxy compartida ─────────────────────────────────
    private Mono<ResponseEntity<byte[]>> proxy(
            ServerWebExchange exchange,
            Mono<byte[]> body,
            String targetBaseUrl) {

        HttpMethod method = exchange.getRequest().getMethod();
        String path = exchange.getRequest().getURI().getRawPath();
        String query = exchange.getRequest().getURI().getRawQuery();

        // Elimina el prefijo /api del path antes de reenviar
        String strippedPath = path.replaceFirst("^/api", "");
        String url = targetBaseUrl + strippedPath + (query != null ? "?" + query : "");

        if (method == HttpMethod.GET || method == HttpMethod.DELETE) {
            return webClient.method(method)
                    .uri(url)
                    .headers(headers -> copyHeaders(exchange.getRequest().getHeaders(), headers))
                    .exchangeToMono(r -> r.toEntity(byte[].class));
        }

        Mono<byte[]> requestBody = body != null ? body : Mono.empty();
        return requestBody
                .defaultIfEmpty(new byte[0])
                .flatMap(payload -> webClient.method(method)
                        .uri(url)
                        .headers(headers -> copyHeaders(exchange.getRequest().getHeaders(), headers))
                        .bodyValue(payload)
                        .exchangeToMono(r -> r.toEntity(byte[].class)));
    }

    private void copyHeaders(HttpHeaders source, HttpHeaders target) {
        source.forEach((key, values) -> {
            if (!HttpHeaders.HOST.equalsIgnoreCase(key)) {
                target.put(key, values);
            }
        });
    }
}