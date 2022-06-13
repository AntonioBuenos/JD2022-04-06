package by.it.arsenihlaz.jd02_02.service;

import by.it.arsenihlaz.jd02_02.entity.*;
import by.it.arsenihlaz.jd02_02.util.RandomGenerator;
import by.it.arsenihlaz.jd02_02.util.Timer;
import by.it.arsenihlaz.jd02_02.interfaces.CustomerAction;
import by.it.arsenihlaz.jd02_02.interfaces.ShoppingCardAction;

public class CustomerWorker extends Thread implements CustomerAction, ShoppingCardAction {
    private final Customer customer;
    private final Shop shop;
    private ShoppingCart shoppingCart;
    private final int timeoutOperation = RandomGenerator.get(100, 300);
    private final int timeoutChoosing = RandomGenerator.get(500, 2000);
    private final static Integer incomingMonitor = 0;
    private final static Integer exitMonitor = 0;
    public static int incomingCounter = 0;
    public static int exitCounter = 0;

    public CustomerWorker(Customer customer, Shop shop) {
        this.customer = customer;
        this.shop = shop;
        shop.getManager().customerEnter();
    }

    @Override
    public void run() {

        enteredStore();
        takeCart();
        System.out.println(customer + " started choosing goods");
        int howManyGoods = customer.numberOfGoods();
        if (howManyGoods == 0) {
            System.out.println(customer + " looked at the prices and decided not to take anything");
        }
        for (int i = 0; i < howManyGoods; i++) {
            Good good = chooseGood();
            putToCart(good);
        }
        System.out.println(customer + " finished choosing goods");
        System.out.println(customer + " in the shopping cart " + shoppingCart.size() + " goods");
        goQueue();
        goOut();
        shop.getManager().customerOut();
    }

    @Override
    public void enteredStore() {
        System.out.println(customer + " enter to the " + shop);
        synchronized (incomingMonitor) {
            incomingCounter++;
        }
    }

    @Override
    public Good chooseGood() {
        String nameGoods = PriceListRepo.getRandomGoods();
        double price = PriceListRepo.getPrice(nameGoods);
        Good good = new Good(nameGoods, price);
        System.out.println(customer + " chose a good: " + good);
        Timer.sleep((int) (timeoutChoosing * customer.speedFactor()));
        return good;
    }

    @Override
    public void goQueue() {
        Queue queue = shop.getQueue();
        synchronized (customer.getMonitor()) {
            System.out.println(customer + " went to the queue");
            queue.add(customer);
            customer.setWaiting(true);
            while (customer.isWaiting()) {
                try {
                    customer.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(customer + " left the queue");
        }
    }

    @Override
    public void goOut() {
        System.out.println(customer + " leaves " + shop);
        synchronized (exitMonitor) {
            exitCounter++;
        }
    }

    @Override
    public void takeCart() {
        shoppingCart = new ShoppingCart();
        System.out.println(customer + " take the cart");
        Timer.sleep((int) (timeoutOperation * customer.speedFactor()));
    }

    @Override
    public int putToCart(Good good) {
        shoppingCart.addGoods(good.getName(), good.getPrice());
        System.out.println(customer + " put the " + good.getName() + " in the cart");
        Timer.sleep((int) (timeoutOperation * customer.speedFactor()));
        return shoppingCart.size();
    }

    protected static int countBuyers() {
        synchronized (exitMonitor) {
            return incomingCounter - exitCounter;
        }
    }

}
