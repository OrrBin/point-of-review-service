//package me.pointofreview.core.data.generator;
//
//import me.pointofreview.core.objects.Code;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.Scanner;
//
//public class CodeGenerator {
//    public static Code[] generateCodes() {
//        Code[] codes = new Code[4];
//        String cwd = System.getProperty("user.dir");
//        File file =
//                new File(cwd + "\\src\\main\\java\\me\\pointofreview\\core\\data\\generator\\codes_for_generator.txt");
//        Scanner sc = null;
//        try {
//            sc = new Scanner(file);
//        } catch (FileNotFoundException e) {
//        }
//
//        for (int i = 0; i < 4; i++) {
//            String language = sc.next();
//            String text = sc.next();
//            Code code = new Code(text, language);
//            codes[i] = code;
//        }
//
//        return codes;
//    }
//}