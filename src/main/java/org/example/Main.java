package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    /**
     * Читает слова из текстового файла и сортирует их по алфавиту.
     *
     * @param filePath путь к текстовому файлу
     * @return отсортированный список слов
     */
    private static List<String> readAndSortWords(String filePath) {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Считывание каждой строки из файла
            while ((line = reader.readLine()) != null) {
                // Разделение строки на слова с использованием пробелов в качестве разделителя
                String[] lineWords = line.split("\\s+");
                // Добавление каждого слова в список слов
                Collections.addAll(words, lineWords);
            }
        } catch (IOException e) {
            // Вывод сообщения об ошибке, если произошла ошибка при чтении файла
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
        // Сортировка слов по алфавиту
        Collections.sort(words);
        return words;
    }

    /**
     * Считает частоту встречаемости каждого слова в текстовом файле.
     *
     * @param words список слов
     * @return карта, содержащая слова в качестве ключей и их частоту вхождений в файле в качестве значений
     */
    private static Map<String, Integer> countWordFrequency(List<String> words) {
        Map<String, Integer> wordFrequencyMap = new TreeMap<>();
        for (String word : words) {
            // Приведение слова к нижнему регистру и удаление знаков препинания
            word = word.replaceAll("[^a-zA-Zа-яА-Я]", "").toLowerCase();
            wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
        }
        return wordFrequencyMap;
    }

    public static void main(String[] args) {
        String filePath = "c:/java/chapter4/word.txt";

        // Чтение и сортировка слов
        List<String> words = readAndSortWords(filePath);

        // Вывод отсортированных слов
//        System.out.println("Отсортированные слова:");
//        for (String word : words) {
//            System.out.println(word);
//        }

        // Подсчет частоты встречаемости слов
        Map<String, Integer> wordFrequencyMap = countWordFrequency(words);

        // Вывод статистики частоты встречаемости слов в алфавитном порядке
        System.out.println("\nСтатистика частоты встречаемости слов:");
        for (Map.Entry<String, Integer> entry : wordFrequencyMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Поиск слова с максимальным количеством повторений
        int maxFrequency = 0;
        String maxWord = null;
        for (Map.Entry<String, Integer> entry : wordFrequencyMap.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                maxFrequency = entry.getValue();
                maxWord = entry.getKey();
            }
        }

        // Вывод слова с максимальным количеством повторений
        System.out.println("\nСлово с максимальным количеством повторений:");
        System.out.println("Слово: " + maxWord);
        System.out.println("Количество повторений: " + maxFrequency);
    }
}
