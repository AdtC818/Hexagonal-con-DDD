package co.edu.uptc.orderservice.shared.application;

public interface UseCase<I, O> {
    O execute(I input);
}
