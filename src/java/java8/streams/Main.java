package java8.streams;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Main {

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

    static int e1(List<String> words) throws InterruptedException {
        int n = Runtime.getRuntime().availableProcessors();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (int i = 0; i < n; i++) {
            new Thread(() -> atomicInteger.updateAndGet(operand -> operand + words.size())).start();
        }
        Thread.sleep(1000);
        return atomicInteger.get();
    }

    static Stream<Character> e6(String s) {
        return Stream.iterate(0, i -> i++).limit(s.length()).map(s::charAt);
    }

    static <T> boolean e7(Stream<T> stream) {
        return stream.spliterator().getExactSizeIfKnown() != -1;
    }

    static <T> Stream<T> e8(Stream<T> first, Stream<T> second) {
        Iterator<T> iterator = second.iterator();
        return first.flatMap(e -> iterator.hasNext() ? Stream.of(e, iterator.next()) : Stream.empty());
    }

    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();

    }
}
