package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nHello! Starting OFF file loading...\n");
        OffService offService = new OffService();
        if(offService.readFile("triangles.off")) offService.menu();
    }
}