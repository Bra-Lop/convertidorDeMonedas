import com.google.gson.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class menuPrincipal {

    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            String cantidad;
            List<ConversionRegistro> registros = new ArrayList<>();
            int opcion = 0;

            do {
                System.out.println("******************************************");
                System.out.println("Sea bienvenido/a al Conversor de Moneda =]");
                System.out.println("******************************************");
                System.out.println(); // Línea en blanco para mejor presentación
                System.out.println("1) Dólar =>> Peso argentino");
                System.out.println("2) Peso argentino =>> Dólar");
                System.out.println("3) Dólar =>> Real brasileño");
                System.out.println("4) Real brasileño =>> Dólar");
                System.out.println("5) Dólar =>> Peso colombiano");
                System.out.println("6) Peso colombiano =>> Dólar");
                System.out.println("7) Mostrar todas las divisas Soportadas");
                System.out.println("8) Convertir otras divisas Soportadas");
                System.out.println("9) Ver registro e historial");
                System.out.println("10) Salir");
                System.out.println(); // Línea en blanco para mejor presentación
                System.out.print("Elija una opción válida: ");


                while (!scanner.hasNextInt()) {
                    System.out.println("Entrada inválida. Por favor, ingrese un número.");
                    scanner.next();
                    System.out.print("Elija una opción válida: ");
                }

                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.println("Escriba la cantidad a convertir " + " USD");
                        cantidad = scanner.nextLine();
                        System.out.println("Convirtiendo de USD a ARS...");
                        try {
                            String resultadoAPI = divisaAPI.obtenerConversion("USD", "ARS", cantidad);
                            try {
                                JsonElement jsonElement = JsonParser.parseString(resultadoAPI);
                                if (jsonElement.isJsonObject()) {
                                    JsonObject jsonObject = jsonElement.getAsJsonObject();

                                    if (jsonObject.has("conversion_result")) {
                                        double resultadoConversion = jsonObject.get("conversion_result").getAsDouble();

                                        LocalDateTime ahora = LocalDateTime.now();
                                        ConversionRegistro registro = new ConversionRegistro(ahora, "USD", "ARS", cantidad, resultadoConversion);
                                        registros.add(registro);

                                        System.out.println("La cantidad a convertir es: " + cantidad + " USD");
                                        System.out.println("La divisa convertida es ARS");
                                        System.out.println("El resultado de la conversión es: " + resultadoConversion + " ARS");
                                    }
                                } else if (jsonElement.isJsonPrimitive()) {
                                    try {
                                        double resultadoConversion = jsonElement.getAsDouble();
                                        LocalDateTime ahora = LocalDateTime.now();
                                        ConversionRegistro registro = new ConversionRegistro(ahora, "USD", "ARS", cantidad, resultadoConversion);
                                        registros.add(registro);
                                    } catch (NumberFormatException e) {
                                        System.out.println("La API retorno un valor primitivo pero no se pudo convertir a numero: " + resultadoAPI);
                                    }
                                } else {
                                    System.out.println("Formato de respuesta desconocido: " + resultadoAPI);
                                }
                            } catch (JsonSyntaxException e) {
                                System.err.println("Error al parsear la respuesta de la API: " + e.getMessage());
                            }

                        } catch (IOException | InterruptedException e) {
                            System.err.println("Error al obtener la conversión: " + e.getMessage());
                        }
                        break;
                    case 2:
                        System.out.println("Escriba la cantidad a convertir " + " ARS");
                        cantidad = scanner.nextLine();
                        System.out.println("Convirtiendo de Peso argentino a Dólar...");
                        try {
                            String resultadoAPI = divisaAPI.obtenerConversion("ARS", "USD", cantidad);
                            System.out.println("Respuesta de la API:\n" + resultadoAPI);

                            try {
                                JsonElement jsonElement = JsonParser.parseString(resultadoAPI);
                                if (jsonElement.isJsonObject()) {
                                    JsonObject jsonObject = jsonElement.getAsJsonObject();

                                    if (jsonObject.has("conversion_result")) {
                                        double resultadoConversion = jsonObject.get("conversion_result").getAsDouble();


                                        LocalDateTime ahora = LocalDateTime.now();
                                        ConversionRegistro registro = new ConversionRegistro(ahora, "ARS", "USD", cantidad, resultadoConversion);
                                        registros.add(registro);


                                        System.out.println("La cantidad a convertir es: " + cantidad + " ARS");
                                        System.out.println("La divisa convertida es USD");
                                        System.out.println("El resultado de la conversión es: " + resultadoConversion + " USD");
                                    } else if (jsonObject.has("error")) {
                                        System.out.println("Error de la API: " + jsonObject.get("error"));
                                    } else {
                                        System.out.println("Respuesta JSON inesperada (sin conversion_result ni error): " + resultadoAPI);
                                    }
                                } else if (jsonElement.isJsonPrimitive()) {
                                    try {
                                        double resultadoConversion = jsonElement.getAsDouble();
                                        LocalDateTime ahora = LocalDateTime.now();
                                        ConversionRegistro registro = new ConversionRegistro(ahora, "ARS", "USD", cantidad, resultadoConversion);
                                        registros.add(registro);
                                        System.out.println("La cantidad a convertir es: " + cantidad + " ARS");
                                        System.out.println("La divisa convertida es USD");
                                        System.out.println("El resultado de la conversión es: " + resultadoConversion + " USD");
                                    } catch (NumberFormatException e) {
                                        System.out.println("La API retorno un valor primitivo pero no se pudo convertir a numero: " + resultadoAPI);
                                    }
                                } else {
                                    System.out.println("Formato de respuesta desconocido: " + resultadoAPI);
                                }
                            } catch (JsonSyntaxException e) {
                                System.err.println("Error al parsear la respuesta de la API: " + e.getMessage());
                            }

                        } catch (IOException | InterruptedException e) {
                            System.err.println("Error al obtener la conversión: " + e.getMessage());
                        }
                        break;
                    case 3:
                        System.out.println("Escriba la cantidad a convertir " + " USD");
                        cantidad = scanner.nextLine();
                        System.out.println("Convirtiendo de Dólar a Real brasileño...");
                        try {
                            String resultadoAPI = divisaAPI.obtenerConversion("USD", "BRL", cantidad);
                            System.out.println("Respuesta de la API:\n" + resultadoAPI);

                            try {
                                JsonElement jsonElement = JsonParser.parseString(resultadoAPI);
                                if (jsonElement.isJsonObject()) {
                                    JsonObject jsonObject = jsonElement.getAsJsonObject();

                                    if (jsonObject.has("conversion_result")) {
                                        double resultadoConversion = jsonObject.get("conversion_result").getAsDouble();


                                        LocalDateTime ahora = LocalDateTime.now();
                                        ConversionRegistro registro = new ConversionRegistro(ahora, "USD", "BRL", cantidad, resultadoConversion);
                                        registros.add(registro);


                                        System.out.println("La cantidad a convertir es: " + cantidad + " USD");
                                        System.out.println("La divisa convertida es BRL");
                                        System.out.println("El resultado de la conversión es: " + resultadoConversion + " BRL");
                                    } else if (jsonObject.has("error")) {
                                        System.out.println("Error de la API: " + jsonObject.get("error"));
                                    } else {
                                        System.out.println("Respuesta JSON inesperada (sin conversion_result ni error): " + resultadoAPI);
                                    }
                                } else if (jsonElement.isJsonPrimitive()) {
                                    try {
                                        double resultadoConversion = jsonElement.getAsDouble();
                                        LocalDateTime ahora = LocalDateTime.now();
                                        ConversionRegistro registro = new ConversionRegistro(ahora, "USD", "BRL", cantidad, resultadoConversion);
                                        registros.add(registro);
                                        System.out.println("La cantidad a convertir es: " + cantidad + " USD");
                                        System.out.println("La divisa convertida es BRL");
                                        System.out.println("El resultado de la conversión es: " + resultadoConversion + " BRL");
                                    } catch (NumberFormatException e) {
                                        System.out.println("La API retorno un valor primitivo pero no se pudo convertir a numero: " + resultadoAPI);
                                    }
                                } else {
                                    System.out.println("Formato de respuesta desconocido: " + resultadoAPI);
                                }
                            } catch (JsonSyntaxException e) {
                                System.err.println("Error al parsear la respuesta de la API: " + e.getMessage());
                            }

                        } catch (IOException | InterruptedException e) {
                            System.err.println("Error al obtener la conversión: " + e.getMessage());
                        }
                        break;
                    case 4:
                        System.out.println("Escriba la cantidad a convertir " + " BRL");
                        cantidad = scanner.nextLine();
                        System.out.println("Convirtiendo de Real brasileño a Dólar...");
                        try {
                            String resultadoAPI = divisaAPI.obtenerConversion("BRL", "USD", cantidad);
                            System.out.println("Respuesta de la API:\n" + resultadoAPI);

                            try {
                                JsonElement jsonElement = JsonParser.parseString(resultadoAPI);
                                if (jsonElement.isJsonObject()) {
                                    JsonObject jsonObject = jsonElement.getAsJsonObject();

                                    if (jsonObject.has("conversion_result")) {
                                        double resultadoConversion = jsonObject.get("conversion_result").getAsDouble();


                                        LocalDateTime ahora = LocalDateTime.now();
                                        ConversionRegistro registro = new ConversionRegistro(ahora, "BRL", "USD", cantidad, resultadoConversion);
                                        registros.add(registro);


                                        System.out.println("La cantidad a convertir es: " + cantidad + " BRL");
                                        System.out.println("La divisa convertida es USD");
                                        System.out.println("El resultado de la conversión es: " + resultadoConversion + " USD");
                                    } else if (jsonObject.has("error")) {
                                        System.out.println("Error de la API: " + jsonObject.get("error"));
                                    } else {
                                        System.out.println("Respuesta JSON inesperada (sin conversion_result ni error): " + resultadoAPI);
                                    }
                                } else if (jsonElement.isJsonPrimitive()) {
                                    try {
                                        double resultadoConversion = jsonElement.getAsDouble();
                                        LocalDateTime ahora = LocalDateTime.now();
                                        ConversionRegistro registro = new ConversionRegistro(ahora, "USD", "BRL", cantidad, resultadoConversion);
                                        registros.add(registro);
                                        System.out.println("La cantidad a convertir es: " + cantidad + " USD");
                                        System.out.println("La divisa convertida es BRL");
                                        System.out.println("El resultado de la conversión es: " + resultadoConversion + " BRL");
                                    } catch (NumberFormatException e) {
                                        System.out.println("La API retorno un valor primitivo pero no se pudo convertir a numero: " + resultadoAPI);
                                    }
                                } else {
                                    System.out.println("Formato de respuesta desconocido: " + resultadoAPI);
                                }
                            } catch (JsonSyntaxException e) {
                                System.err.println("Error al parsear la respuesta de la API: " + e.getMessage());
                            }

                        } catch (IOException | InterruptedException e) {
                            System.err.println("Error al obtener la conversión: " + e.getMessage());
                        }
                        break;
                    case 5:
                        System.out.println("Escriba la cantidad a convertir " + " USD");
                        cantidad = scanner.nextLine();
                        System.out.println("Convirtiendo de Dólar a Peso colombiano...");
                        try {
                            String resultadoAPI = divisaAPI.obtenerConversion("USD", "COP", cantidad);
                            System.out.println("Respuesta de la API:\n" + resultadoAPI);

                            try {
                                JsonElement jsonElement = JsonParser.parseString(resultadoAPI);
                                if (jsonElement.isJsonObject()) {
                                    JsonObject jsonObject = jsonElement.getAsJsonObject();

                                    if (jsonObject.has("conversion_result")) {
                                        double resultadoConversion = jsonObject.get("conversion_result").getAsDouble();


                                        LocalDateTime ahora = LocalDateTime.now();
                                        ConversionRegistro registro = new ConversionRegistro(ahora, "USD", "COP", cantidad, resultadoConversion);
                                        registros.add(registro);


                                        System.out.println("La cantidad a convertir es: " + cantidad + " USD");
                                        System.out.println("La divisa convertida es COP");
                                        System.out.println("El resultado de la conversión es: " + resultadoConversion + " COP");
                                    } else if (jsonObject.has("error")) {
                                        System.out.println("Error de la API: " + jsonObject.get("error"));
                                    } else {
                                        System.out.println("Respuesta JSON inesperada (sin conversion_result ni error): " + resultadoAPI);
                                    }
                                } else if (jsonElement.isJsonPrimitive()) {
                                    try {
                                        double resultadoConversion = jsonElement.getAsDouble();
                                        LocalDateTime ahora = LocalDateTime.now();
                                        ConversionRegistro registro = new ConversionRegistro(ahora, "USD", "COP", cantidad, resultadoConversion);
                                        registros.add(registro);
                                        System.out.println("La cantidad a convertir es: " + cantidad + " USD");
                                        System.out.println("La divisa convertida es COP");
                                        System.out.println("El resultado de la conversión es: " + resultadoConversion + " COP");
                                    } catch (NumberFormatException e) {
                                        System.out.println("La API retorno un valor primitivo pero no se pudo convertir a numero: " + resultadoAPI);
                                    }
                                } else {
                                    System.out.println("Formato de respuesta desconocido: " + resultadoAPI);
                                }
                            } catch (JsonSyntaxException e) {
                                System.err.println("Error al parsear la respuesta de la API: " + e.getMessage());
                            }

                        } catch (IOException | InterruptedException e) {
                            System.err.println("Error al obtener la conversión: " + e.getMessage());
                        }
                        break;
                    case 6:
                        System.out.println("Escriba la cantidad a convertir " + " COP");
                        cantidad = scanner.nextLine();
                        System.out.println("Convirtiendo de Peso colombiano a Dólar...");
                        try {
                            String resultadoAPI = divisaAPI.obtenerConversion("COP", "USD", cantidad);
                            System.out.println("Respuesta de la API:\n" + resultadoAPI);

                            try {
                                JsonElement jsonElement = JsonParser.parseString(resultadoAPI);
                                if (jsonElement.isJsonObject()) {
                                    JsonObject jsonObject = jsonElement.getAsJsonObject();

                                    if (jsonObject.has("conversion_result")) {
                                        double resultadoConversion = jsonObject.get("conversion_result").getAsDouble();


                                        LocalDateTime ahora = LocalDateTime.now();
                                        ConversionRegistro registro = new ConversionRegistro(ahora, "COP", "USD", cantidad, resultadoConversion);
                                        registros.add(registro);


                                        System.out.println("La cantidad a convertir es: " + cantidad + " COP");
                                        System.out.println("La divisa convertida es BRL");
                                        System.out.println("El resultado de la conversión es: " + resultadoConversion + " USD");
                                    } else if (jsonObject.has("error")) {
                                        System.out.println("Error de la API: " + jsonObject.get("error"));
                                    } else {
                                        System.out.println("Respuesta JSON inesperada (sin conversion_result ni error): " + resultadoAPI);
                                    }
                                } else if (jsonElement.isJsonPrimitive()) {
                                    try {
                                        double resultadoConversion = jsonElement.getAsDouble();
                                        LocalDateTime ahora = LocalDateTime.now();
                                        ConversionRegistro registro = new ConversionRegistro(ahora, "COP", "USD", cantidad, resultadoConversion);
                                        registros.add(registro);
                                        System.out.println("La cantidad a convertir es: " + cantidad + " COP");
                                        System.out.println("La divisa convertida es USD");
                                        System.out.println("El resultado de la conversión es: " + resultadoConversion + " USD");
                                    } catch (NumberFormatException e) {
                                        System.out.println("La API retorno un valor primitivo pero no se pudo convertir a numero: " + resultadoAPI);
                                    }
                                } else {
                                    System.out.println("Formato de respuesta desconocido: " + resultadoAPI);
                                }
                            } catch (JsonSyntaxException e) {
                                System.err.println("Error al parsear la respuesta de la API: " + e.getMessage());
                            }

                        } catch (IOException | InterruptedException e) {
                            System.err.println("Error al obtener la conversión: " + e.getMessage());
                        }
                        break;
                    case 7:
                        System.out.println("\nListado completo de Divisas Soportadas");
                        try {
                            DivisasAPIGson.obtenerDivisas();
                        } catch (IOException e) {
                            System.err.println("Error al obtener las divisas: " + e.getMessage());}
                        break;
                    case 8:
                        Scanner lectura = new Scanner(System.in);
                        System.out.println("Escriba la divisa a convertir");
                        String monedaInicial = lectura.nextLine();
                        System.out.println("Escriba la divisa de destino");
                        String monedaFinal = lectura.nextLine();
                        System.out.println("Escriba la cantidad a convertir " + monedaInicial);
                        cantidad = scanner.nextLine();
                        System.out.println("Convirtiendo de   " + monedaInicial + "  a " + monedaFinal + "...");
                        try {
                            String resultado = divisaAPI.obtenerConversion(monedaInicial, monedaFinal, cantidad);
                            System.out.println("Resultado de la conversión:\n" + resultado);
                            Gson gson = new Gson();
                            JsonObject jsonObject = gson.fromJson(resultado, JsonObject.class);
                            if (jsonObject.has("conversion_result")) {
                                double resultadoConversion = jsonObject.get("conversion_result").getAsDouble();

                                // *** CREACIÓN Y ADICIÓN DEL REGISTRO (CASE 8) ***
                                LocalDateTime ahora = LocalDateTime.now();
                                ConversionRegistro registro = new ConversionRegistro(ahora, monedaInicial, monedaFinal, cantidad, resultadoConversion);
                                registros.add(registro);
                                // *** FIN DE LA CREACIÓN DEL REGISTRO ***

                                System.out.println("La divisa a convertir es " + monedaInicial);
                                System.out.println("La divisa convertida es " + monedaFinal);
                                System.out.println("El resultado de la conversion es: " + resultadoConversion + (" ") + monedaFinal);
                            } else {
                                System.out.println("Error en la conversion");
                            }
                        } catch (IOException | InterruptedException e) {
                            System.err.println("Error al obtener la conversión: " + e.getMessage());
                        }
                        break;

                    case 9:
                        System.out.println("\nHistorial de Conversiones:");
                        if (registros.isEmpty()) {
                            System.out.println("No se han realizado conversiones aún.");
                        } else {
                            for (ConversionRegistro registro : registros) {
                                System.out.println(registro);
                            }
                        }
                        break;
                    case 10:
                        System.out.println("Saliendo del programa.");
                        break;
                        default:System.out.println("Opción no válida. Por favor, elija una opción del 1 al 9.");
                }

                System.out.println();
            } while (opcion != 10);
            scanner.close();

        }
    }
