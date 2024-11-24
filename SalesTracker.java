import java.util.LinkedList;
public class SalesTracker {

    // Класс для представления товара
    public static class Product {
        private String name; // Название товара
        private double price; // Цена товара

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return "Товар: " + name + ", Цена: " + price;
        }
    }

    // Основной класс для учета продаж
    private LinkedList<Product> soldProducts;

    public SalesTracker() {
        this.soldProducts = new LinkedList<>();
    }

    // Метод для добавления проданного товара
    public void addProduct(String name, double price) {
        soldProducts.add(new Product(name, price));
    }

    // Метод для вывода списка проданных товаров
    public void printSoldProducts() {
        if (soldProducts.isEmpty()) {
            System.out.println("Проданные товары отсутствуют.");
            return;
        }
        System.out.println("Список проданных товаров:");
        for (Product product : soldProducts) {
            System.out.println(product);
        }
    }

    // Метод для подсчета общей суммы продаж
    public double getTotalSales() {
        double total = 0;
        for (Product product : soldProducts) {
            total += product.getPrice();
        }
        return total;
    }

    // Метод для определения самого популярного товара
    public String getMostPopularProduct() {
        if (soldProducts.isEmpty()) {
            return "Нет проданных товаров.";
        }

        // Подсчет количества продаж каждого товара
        LinkedList<String> productNames = new LinkedList<>();
        LinkedList<Integer> counts = new LinkedList<>();

        for (Product product : soldProducts) {
            String name = product.getName();
            int index = productNames.indexOf(name);
            if (index != -1) {
                counts.set(index, counts.get(index) + 1);
            } else {
                productNames.add(name);
                counts.add(1);
            }
        }

        // Поиск товара с максимальным количеством продаж
        int maxIndex = 0;
        for (int i = 1; i < counts.size(); i++) {
            if (counts.get(i) > counts.get(maxIndex)) {
                maxIndex = i;
            }
        }

        return productNames.get(maxIndex);
    }

    // Метод main для тестирования
    public static void main(String[] args) {
        SalesTracker tracker = new SalesTracker();

        // Добавляем проданные товары
        tracker.addProduct("Хлеб", 1.20);
        tracker.addProduct("Молоко", 0.95);
        tracker.addProduct("Сыр", 2.50);
        tracker.addProduct("Хлеб", 1.20);
        tracker.addProduct("Яблоко", 0.70);
        tracker.addProduct("Хлеб", 1.20);

        // Выводим список проданных товаров
        tracker.printSoldProducts();

        // Считаем общую сумму продаж
        System.out.println("Общая сумма продаж: " + tracker.getTotalSales());

        // Определяем наиболее популярный товар
        System.out.println("Наиболее популярный товар: " + tracker.getMostPopularProduct());
    }
}