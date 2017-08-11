package java8.streams;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.time.*;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class ExampleStream {

    public static <T> Image transform(Image im, BiFunction<Color, T, Color> f, T arg) {
        int width = (int) im.getWidth();
        int height = (int) im.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                out.getPixelWriter().setColor(x, y, f.apply(im.getPixelReader().getColor(x, y), arg));
            }
        }
        return out;
    }

    public static <T> void forEach(Consumer<? super T> action, T arg) {
        action.accept(arg);
    }

    public static void doInOrderAsync(Runnable first, Runnable second) {
        Thread t = new Thread(() -> {
            first.run();
            second.run();
         });
        t.start();
    }

    public static void main(String[] args) {
        Stream<String> stream = Stream.of(" Art ezio,Exadel,Epam,Andersen,InnowiseGroup".split(","));
        Stream<String> stream1 = Stream.of("ВГУ ВГТУ ВГМУ ВГАВМ".split(" "));


    }
}
