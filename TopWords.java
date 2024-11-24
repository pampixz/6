import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TopWords {
    public static void main(String[] args) {
        // указываем путь к файлу
        String filePath = "C:\\Users\\Professional\\IdeaProjects\\untitled\\text.txt";
        // создаем объект File
        File file = new File(filePath);
        // создаем объект Scanner для чтения файла
        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
            return;
        }

        // создаем объект Map для хранения слов и их количества
        Map<String, Integer> wordCounts = new HashMap<>();

        // читаем файл по словам и добавляем их в Map
        while (scanner.hasNext()) {
            // считываем слово и приводим его к нижнему регистру
            String word = scanner.next().toLowerCase().replaceAll("[^a-zа-я0-9]", "");
            if (!word.isEmpty()) {
                wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
            }
        }

        // закрываем Scanner
        scanner.close();

        // создаем список из элементов Map
        List<Map.Entry<String, Integer>> list = new ArrayList<>(wordCounts.entrySet());

        // сортируем список по убыванию количества повторений
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        // выводим топ-10 слов
        System.out.println("Топ-10 самых часто встречающихся слов:");
        for (int i = 0; i < Math.min(10, list.size()); i++) {
            Map.Entry<String, Integer> entry = list.get(i);
            System.out.println((i + 1) + ". " + entry.getKey() + " - " + entry.getValue() + " раз(а)");
        }
    }
}