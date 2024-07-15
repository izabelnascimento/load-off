package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OffService {
    List<Vertex> vertices;
    List<Face> faces;

    public OffService() {
        vertices = new ArrayList<>();
        faces = new ArrayList<>();
    }

    public void readOFF(String fileName) throws IOException {
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IOException("File " + fileName + " not found");
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line = br.readLine();
        if (!line.equals("OFF")) {
            br.close();
            throw new IOException("Not a valid OFF file.");
        }

        line = br.readLine();
        String[] parts = line.split(" ");
        int numPoints = Integer.parseInt(parts[0]);
        int numFaces = Integer.parseInt(parts[1]);

        for (int i = 0; i < numPoints; i++) {
            line = br.readLine();
            parts = line.split(" ");
            double x = Double.parseDouble(parts[0]);
            double y = Double.parseDouble(parts[1]);
            double z = Double.parseDouble(parts[2]);
            vertices.add(new Vertex(x, y, z));
        }

        for (int i = 0; i < numFaces; i++) {
            line = br.readLine();
            parts = line.split(" ");
            int numVertices = Integer.parseInt(parts[0]);
            List<Integer> vertices = new ArrayList<>();
            for (int j = 1; j <= numVertices; j++) {
                vertices.add(Integer.parseInt(parts[j]));
            }
            faces.add(new Face(vertices));
        }

        br.close();
    }

    public boolean readFile(String fileName){
        try {
            readOFF(fileName);
            System.out.println("File read successfully.");
            return true;
        } catch (IOException e) {
            System.out.println("ERROR - " + e.getMessage());
        }
        return false;
    }

    public void menu(){
        int op;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("\nChoose an option:\n" +
                    "1. Print the vertexes. \n" +
                    "   Example: vertex n: x y z\n" +
                    "2. Print the faces. \n" +
                    "   Example: face n: v1 v2 v3 v4\n" +
                    "3. Print the faces with vertexes. \n" +
                    "   Example: face n: v1(x, y, z) v2(x, y, z) v3(x, y, z) v4(x, y, z)\n" +
                    "4. Print all\n" +
                    "0. Exit\n");
            op = scanner.nextInt();
            switch (op) {
                case 1:
                    printVertexes();
                    break;
                case 2:
                    printFaces();
                    break;
                case 3:
                    printFacesWithVertexes();
                    break;
                case 4:
                    printVertexes();
                    printFaces();
                    printFacesWithVertexes();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        } while (op != 0);
    }

    public void printVertexes() {
        System.out.println("\n-------------------------------------------------");
        System.out.println("Starting printing vertexes...");
        int i = 0;
        for (Vertex p : vertices) {
            System.out.print("vertex " + i + ": " + p.x + " " + p.y + " " + p.z + "\n");
            i++;
        }
        System.out.println("-------------------------------------------------\n");
    }

    public void printFaces() {
        System.out.println("\n-------------------------------------------------");
        System.out.println("Starting printing vertexes...");
        int i = 0;
        for (Face f : faces) {
            System.out.print("face " + i + ": ");
            for (int v : f.vertices) {
                Vertex p = vertices.get(v);
                System.out.print(v + p.x + p.y + p.z + " ");
            }
            System.out.println();
            i++;
        }
        System.out.println("-------------------------------------------------\n");
    }

    public void printFacesWithVertexes() {
        System.out.println("\n-------------------------------------------------");
        System.out.println("Starting printing faces with vertexes...");
        int i = 0;
        for (Face f : faces) {
            System.out.print("face " + i + ": ");
            for (int v : f.vertices) {
                Vertex p = vertices.get(v);
                System.out.print(v + "(" + p.x + ", " + p.y + ", " + p.z + ")" + " ");
            }
            System.out.println();
            i++;
        }
        System.out.println("-------------------------------------------------\n");
    }
}
