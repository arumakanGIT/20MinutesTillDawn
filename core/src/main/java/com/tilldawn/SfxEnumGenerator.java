package com.tilldawn;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class SfxEnumGenerator {

    public static void main(String[] args) throws IOException {
        Path root = Paths.get("C:\\Users\\abomo\\IdeaProjects\\advanced-programming-phase-1-group-56\\assets\\SFXs");

        Path outputFile = Paths.get("C:\\Users\\abomo\\IdeaProjects\\advanced-programming-phase-1-group-56\\core\\src\\main\\java\\io\\src\\model\\Enums\\SfxEnum.java");

        StringBuilder enumBuilder = new StringBuilder();
        enumBuilder.append("package io.src.model.Enums;\n\npublic enum SfxEnum {\n\n");

        try (Stream<Path> paths = Files.walk(root)) {
            paths.filter(Files::isRegularFile).forEach(file -> {
                Path relativePath = root.relativize(file);

                // گرفتن نام فایل بدون پسوند
                String fileName = file.getFileName().toString();
                int dotIndex = fileName.lastIndexOf(".");
                String nameWithoutExt = (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);

                // ساخت اسم ثابت Enum (پوشه‌ها + اسم بدون پسوند)
                String enumName = root.relativize(file)
                    .toString()
                    .replace(File.separator, "_")
                    .replace(fileName, nameWithoutExt) // حذف پسوند از اسم enum
                    .replaceAll("\\W+", "_")
                    .replaceAll("_+", "_")
                    .toUpperCase();

                // مسیر با پسوند برای پخش
                String pathString = root.getFileName() + "/" + relativePath.toString().replace("\\", "/");

                enumBuilder.append("    ")
                    .append(enumName)
                    .append("(\"")
                    .append(pathString)
                    .append("\"),\n");
            });
        }

        // حذف ویرگول آخر
        int lastComma = enumBuilder.lastIndexOf(",");
        if (lastComma != -1) {
            enumBuilder.replace(lastComma, lastComma + 1, ";");
        }

        enumBuilder.append("\n    public final String path;\n\n")
            .append("    SfxEnum(String path) {\n")
            .append("        this.path = path;\n")
            .append("    }\n")
            .append("public String getPath() { return path; }")
            .append("}\n");

        // نوشتن در فایل
        try (FileWriter writer = new FileWriter(outputFile.toFile())) {
            writer.write(enumBuilder.toString());
        }

        System.out.println("Enum file created at: " + outputFile.toAbsolutePath());
    }
}
