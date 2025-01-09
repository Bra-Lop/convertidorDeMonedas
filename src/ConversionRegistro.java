import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class ConversionRegistro { // Clase para representar un registro de conversión
    public LocalDateTime fechaHora;
    public String monedaOrigen;
    public String monedaDestino;
    public String cantidadOrigen;
    public double resultado;

    public ConversionRegistro(LocalDateTime fechaHora, String monedaOrigen, String monedaDestino, String cantidadOrigen, double resultado) {
        this.fechaHora = fechaHora;
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
        this.cantidadOrigen = cantidadOrigen;
        this.resultado = resultado;
    }

    @Override
    public String toString() { // Para mostrar el registro de forma legible
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "Fecha/Hora: " + fechaHora.format(formatter) +
                ", Conversión: " + cantidadOrigen + " " + monedaOrigen +
                " a " + resultado + " " + monedaDestino;
    }
}
