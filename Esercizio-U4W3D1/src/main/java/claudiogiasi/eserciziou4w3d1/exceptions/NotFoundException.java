package claudiogiasi.eserciziou4w3d1.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("Nessun post trovato con id: " + id);
    }
}
