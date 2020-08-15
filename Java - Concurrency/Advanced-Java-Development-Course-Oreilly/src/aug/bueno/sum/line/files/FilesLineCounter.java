package aug.bueno.sum.line.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class FilesLineCounter {

    // Directory with the files in it
    private Path dir = Paths.get("src", "aug", "bueno", "sum", "line", "files");

    public long computeTotalNumberOfLines() {

        long total = 0;

        try {
            total = executeCounters().stream()
                    .mapToLong(this::extractValuesFromFuture)
                    .sum();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

        return total;
    }


    private List<Future<Long>> executeCounters() throws InterruptedException, IOException {

        ExecutorService service = Executors.newCachedThreadPool();
        List<Future<Long>> futures = service.invokeAll(getFileCounters());

        service.shutdown();

        return futures;
    }

    private List<Callable<Long>> getFileCounters() throws IOException {

        return Files.list(dir) // returns the entries of the directory
                .filter(Files::isRegularFile) // perform filter to remove the directories from the returned objects
                .map(this::callableCounter)
                .collect(Collectors.toList());
    }

    private Callable<Long> callableCounter(Path path) {

        return () -> {
            long count = Files.lines(path).count();
            System.out.printf("%s has %d lines %n", path, count);

            return count;
        };
    }


    private <T> T extractValuesFromFuture(Future<T> future) {

        T val = null;

        try {
            val = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return val;
    }


    public static void main(String[] args) {
        FilesLineCounter underTest = new FilesLineCounter();

        System.out.println("Total number of lies: " + underTest.computeTotalNumberOfLines());
    }

}
