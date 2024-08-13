package com.example.behavioral_patterns._22_template.after;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public abstract class FileProcessor {

    private String path;
    public FileProcessor(String path) {
        this.path = path;
    }

    /**
     * Template method
     */
    public final int process(Operator operator) {
        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            int result = 0;
            String line = null;
            while((line = reader.readLine()) != null) {
                // extract method refactoring
                result = getResult(result, Integer.parseInt(line));
            }
            return result;
        } catch (IOException e) {
            throw new IllegalArgumentException(path + "에 해당하는 파일이 없습니다.", e);
        }
    }

    /**
     * 하위 클래스에서 변경할 클래스 >> protected,  abstract
     * @param result
     * @param number
     * @return
     */
    protected abstract int getResult(int result, int number);

}
