public int solution(String[][] clothes) {
    Map<String, Integer> typeCount = new HashMap<>();

    for (String[] item : clothes) {
        String type = item[1];
        typeCount.put(type, typeCount.getOrDefault(type, 0) + 1);
    }

    int result = 1;
    for (int count : typeCount.values()) {
        result *= (count + 1); // 안 입는 경우까지 포함
    }

    return result - 1; // 아무것도 안 입는 경우 제외
}