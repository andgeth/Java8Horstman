package java8.lambdas;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Main {

    static File[] e2(String path) {
        File file = new File(path);
        File[] files = null;
        if (file.isDirectory()) {
             files = file.listFiles(f -> f.getName().length() < 30);
        }
        return files;
    }

    static File[] e3(String path, String extension) {
        File file = new File(path);
        Pattern pattern = Pattern.compile(".*\\." + extension);
        return file.listFiles((dir, name) -> pattern.asPredicate().test(name));
    }

    static File[] e4(File[] files) {
        Arrays.sort(files, (f1, f2) -> {
            if ((f1.isDirectory() && f2.isDirectory()) || (!f1.isDirectory() && !f2.isDirectory())) {
                return f1.getName().compareTo(f2.getName());
            } else {
                return f1.isDirectory() && !f2.isDirectory() ? -1 : !f1.isDirectory() && f2.isDirectory() ? 1 : 0;
            }
        });
        return files;
    }

    interface forE5 {
        void run() throws Throwable;
    }

    static Runnable e5(Callable<Void> forE5) {
        return () -> {
            try {
                forE5.call();
            } catch (Throwable throwable) {
                System.out.println("There is a problem!");
            }
        };
    }

    static Runnable e9(Runnable run1, Runnable run2) {
        return () -> {
            run1.run();
            run2.run();
        };
    }

    static class e12<T> extends ArrayList<T> implements Collection<T> {

        Object[] tArray;
        private static final int DEFAULT_CAPACITY = 10;
        private int size = 0;

        public e12() {
            this.tArray = new Object[DEFAULT_CAPACITY];
        }
        @Override
        public int size() {
            return size;
        }
        @Override
        public boolean isEmpty() {
            return tArray == null || tArray.length == 0;
        }
        @Override
        public boolean contains(Object o) {
            return false;
        }
        @Override
        public Iterator<T> iterator() {
            return null;
        }
        @Override
        public Object[] toArray() {
            return new Object[0];
        }
        @Override
        public <T1> T1[] toArray(T1[] a) {
            return null;
        }
        @Override
        public boolean add(T t) {
            if (size < tArray.length - 1) {
                tArray[size++] = t;
                return true;
            }
            else return false;
        }
        @Override
        public boolean remove(Object o) {
            return false;
        }
        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }
        @Override
        public boolean addAll(Collection<? extends T> c) {
            return false;
        }
        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }
        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }
        @Override
        public void clear() {

        }
        @Override
        public boolean equals(Object o) {
            return false;
        }
        @Override
        public int hashCode() {
            return 0;
        }
        void forEachIf(Consumer<T> action, Predicate<T> filter) {
            for (int i = 0; i < size; i++) {
                if (filter.test((T) tArray[i])) {
                    action.accept(((T) tArray[i]));
                }
            }
        }
    }

    public static void main(String[] args) {


    }

}