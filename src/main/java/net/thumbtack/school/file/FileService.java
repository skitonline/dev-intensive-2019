package net.thumbtack.school.file;

import com.google.gson.Gson;
import net.thumbtack.school.colors.v3.Color;
import net.thumbtack.school.colors.v3.ColorException;
import net.thumbtack.school.figures.v3.*;
import net.thumbtack.school.ttschool.Trainee;
import net.thumbtack.school.ttschool.TrainingException;

import java.io.*;

public class FileService {
    public static void  writeByteArrayToBinaryFile(String fileName, byte[] array)  throws IOException{
        try(FileOutputStream fos = new FileOutputStream(fileName)) {
            fos.write(array);
        }
        catch(IOException e){ }
    }

    public static void  writeByteArrayToBinaryFile(File file, byte[] array) throws IOException{
        try {
            writeByteArrayToBinaryFile(file.getPath(), array);
        } catch (IOException e) {}
    }

    public static byte[]  readByteArrayFromBinaryFile(String fileName) throws IOException{
        byte[] result = null;
        try(FileInputStream fis = new FileInputStream(fileName)){
            result = new byte[(int)fis.getChannel().size()];
            fis.read(result);
        } catch (IOException e) { }
        return result;
    }

    public static byte[]  readByteArrayFromBinaryFile(File file) throws IOException {
        byte[] result = null;
        try {
            result = readByteArrayFromBinaryFile(file.getPath());
        } catch (IOException e) {}
        return result;
    }

    public static byte[]  writeAndReadByteArrayUsingByteStream( byte[] array) throws IOException{
        byte[] result = null;
        final int SIZE = array.length;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            for (int i = 0; i < SIZE; i += 2)
                baos.write(array[i]);
            result = baos.toByteArray();
        } catch (IOException e) { }
        return result;
    }

    public static void  writeByteArrayToBinaryFileBuffered(String fileName, byte[] array) throws IOException{
        try(FileOutputStream out = new FileOutputStream(fileName);
            BufferedOutputStream bos = new BufferedOutputStream(out)) {
            bos.write(array);
        }
        catch(IOException e){ }
    }

    public static void  writeByteArrayToBinaryFileBuffered(File file, byte[] array) throws IOException{
        try {
            writeByteArrayToBinaryFileBuffered(file.getPath(), array);
        } catch (IOException e) {}

    }

    public static byte[] readByteArrayFromBinaryFileBuffered(String fileName) throws IOException{
        byte[] result = null;
        try(FileInputStream file = new FileInputStream(fileName);
            BufferedInputStream bis = new BufferedInputStream(file)){
            result = new byte[(int)file.getChannel().size()];
            bis.read(result);
        }
        catch(IOException e){ }
        return result;
    }

    public static byte[] readByteArrayFromBinaryFileBuffered(File file) throws IOException{
        byte[] result = null;
        try {
            result = readByteArrayFromBinaryFileBuffered(file.getPath());
        } catch (IOException e) {}
        return result;
    }

    public static void  writeRectangleToBinaryFile(File file, Rectangle rect) throws IOException{
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.writeInt(rect.getTopLeft().getX());
            raf.writeInt(rect.getTopLeft().getY());
            raf.writeInt(rect.getBottomRight().getX());
            raf.writeInt(rect.getBottomRight().getY());
        } catch (FileNotFoundException e) { } catch (IOException e) { }
    }

    public static Rectangle  readRectangleFromBinaryFile(File file) throws IOException{
        int xLeft =  0, yTop = 0, xRight = 0, yBottom = 0;
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            xLeft = raf.readInt();
            yTop = raf.readInt();
            xRight = raf.readInt();
            yBottom = raf.readInt();
        } catch (FileNotFoundException e) { } catch (IOException e) { }
        return new Rectangle(xLeft, yTop, xRight, yBottom);
    }

    public static void  writeColoredRectangleToBinaryFile(File file, ColoredRectangle rect) throws IOException{
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.writeInt(rect.getTopLeft().getX());
            raf.writeInt(rect.getTopLeft().getY());
            raf.writeInt(rect.getBottomRight().getX());
            raf.writeInt(rect.getBottomRight().getY());
            raf.writeUTF(rect.getColor().toString());
        } catch (FileNotFoundException e) { } catch (IOException e) { }
    }

    public static ColoredRectangle  readColoredRectangleFromBinaryFile(File file) throws ColorException, IOException {
        int xLeft =  0, yTop = 0, xRight = 0, yBottom = 0;
        String color = null;
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            xLeft = raf.readInt();
            yTop = raf.readInt();
            xRight = raf.readInt();
            yBottom = raf.readInt();
            color = raf.readUTF();
        } catch (FileNotFoundException e) { } catch (IOException e) { }
        return new ColoredRectangle(xLeft, yTop, xRight, yBottom, color);
    }

    public static void  writeRectangleArrayToBinaryFile(File file, Rectangle[] rects) throws IOException{
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            for (Rectangle rect : rects) {
                raf.writeInt(rect.getTopLeft().getX());
                raf.writeInt(rect.getTopLeft().getY());
                raf.writeInt(rect.getBottomRight().getX());
                raf.writeInt(rect.getBottomRight().getY());
            }
        } catch (FileNotFoundException e) { } catch (IOException e) { }
    }

    public static Rectangle[]  readRectangleArrayFromBinaryFileReverse(File file) throws IOException{
        int count = (int)file.length() / 16;
        Rectangle[] result = new Rectangle[count];
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            for (int i = 0; i < count; i++) {
                int xLeft = 0, yTop = 0, xRight = 0, yBottom = 0;
                xLeft = raf.readInt();
                yTop = raf.readInt();
                xRight = raf.readInt();
                yBottom = raf.readInt();
                result[i] = new Rectangle(xLeft, yTop, xRight, yBottom);
            }
        } catch (FileNotFoundException e) { } catch (IOException e) { }
        for (int i = 0; i < count / 2; i++) {
            Rectangle t = result[i];
            result[i] = result[count - i - 1];
            result[count - i - 1] = t;
        }
        return result;
    }

    public static void writeRectangleToTextFileOneLine(File file, Rectangle rect) throws IOException{
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
            bw.write(rect.toString());
            bw.newLine();
        } catch (FileNotFoundException e) { } catch (UnsupportedEncodingException e) { } catch (IOException e) { }
    }

    public static Rectangle readRectangleFromTextFileOneLine(File file) throws IOException{
        int xLeft =  0, yTop = 0, xRight = 0, yBottom = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String str = br.readLine();
            String[] arr = str.split(" ");
            xLeft = Integer.parseInt(arr[0]);
            yTop = Integer.parseInt(arr[1]);
            xRight = Integer.parseInt(arr[2]);
            yBottom = Integer.parseInt(arr[3]);
        } catch (IOException e) { }
        return new Rectangle(xLeft, yTop, xRight, yBottom);
    }

    public static void writeRectangleToTextFileFourLines(File file, Rectangle rect) throws IOException{
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
            bw.write(String.valueOf(rect.getTopLeft().getX()));
            bw.newLine();
            bw.write(String.valueOf(rect.getTopLeft().getY()));
            bw.newLine();
            bw.write(String.valueOf(rect.getBottomRight().getX()));
            bw.newLine();
            bw.write(String.valueOf(rect.getBottomRight().getY()));
            bw.newLine();
        } catch (FileNotFoundException e) { } catch (UnsupportedEncodingException e) { } catch (IOException e) { }
    }

    public static Rectangle readRectangleFromTextFileFourLines(File file) throws IOException{
        int xLeft =  0, yTop = 0, xRight = 0, yBottom = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            xLeft = Integer.parseInt(br.readLine());
            yTop = Integer.parseInt(br.readLine());
            xRight = Integer.parseInt(br.readLine());
            yBottom = Integer.parseInt(br.readLine());
        } catch (IOException e) { }
        return new Rectangle(xLeft, yTop, xRight, yBottom);
    }

    public static void writeTraineeToTextFileOneLine(File file, Trainee trainee) throws IOException{
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
            bw.write(trainee.toString());
            bw.newLine();
        } catch (FileNotFoundException e) { } catch (UnsupportedEncodingException e) { } catch (IOException e) { }
    }

    public static Trainee readTraineeFromTextFileOneLine(File file) throws TrainingException, IOException{
        String firstName = null, lastName = null;
        int rating = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String str = br.readLine();
            String[] arr = str.split(" ");
            firstName = arr[0];
            lastName = arr[1];
            rating = Integer.parseInt(arr[2]);
        } catch (IOException e) { }
        return new Trainee(firstName, lastName, rating);
    }

    public static void  writeTraineeToTextFileThreeLines(File file, Trainee trainee) throws IOException{
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
            bw.write(trainee.getFirstName());
            bw.newLine();
            bw.write(trainee.getLastName());
            bw.newLine();
            bw.write(String.valueOf(trainee.getRating()));
            bw.newLine();
        } catch (FileNotFoundException e) { } catch (UnsupportedEncodingException e) { } catch (IOException e) { }
    }

    public static Trainee  readTraineeFromTextFileThreeLines(File file) throws TrainingException, IOException{
        String firstName = null, lastName = null;
        int rating = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            firstName = br.readLine();
            lastName = br.readLine();
            rating = Integer.parseInt(br.readLine());
        } catch (IOException e) { }
        return new Trainee(firstName, lastName, rating);
    }

    public static void  serializeTraineeToBinaryFile(File file, Trainee trainee) throws IOException{
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(trainee);
        } catch(IOException e){ }
    }

    public static Trainee  deserializeTraineeFromBinaryFile(File file) throws IOException{
        Trainee result = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            result = (Trainee)ois.readObject();
        } catch (IOException e) { } catch (ClassNotFoundException e) { }
        return result;
    }

    public static String  serializeTraineeToJsonString(Trainee trainee){
        Gson gson = new Gson();
        return gson.toJson(trainee);
    }

    public static Trainee  deserializeTraineeFromJsonString(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, Trainee.class);
    }

    public static void  serializeTraineeToJsonFile(File file, Trainee trainee) throws IOException{
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            Gson gson = new Gson();
            gson.toJson(trainee, bw);
        } catch (IOException e) { }
    }

    public static Trainee  deserializeTraineeFromJsonFile(File file) throws IOException{
        Trainee result = null;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            Gson gson = new Gson();
            result = gson.fromJson(br,Trainee.class);
        } catch (IOException e) { }
        return result;
    }
}
