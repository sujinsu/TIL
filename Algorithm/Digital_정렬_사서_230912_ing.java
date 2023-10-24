import java.util.*;

/**
 * 아직 더 풀어야 함
 */
public class Digital_정렬_사서_230912_ing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();
        List<String> books = new ArrayList<>();
        for(int i=0; i<n;i++){
            books.add(sc.nextLine());
        }

        sort(books,n);
    }

    public static void sort(List<String> books, int n){

        Collections.sort(books, Comparator.comparingInt(String::length));
        // Collections.sort(books);
        // for(int i=0;i<n-1;i++){
        //     int idx = i;
        //     for(int j=i+1;j<n; j++){
        //         if(books.get(idx).length()< books.get(j).length()){
        //             String temp = books.get(idx);
        //             books.set(idx,books.get(j));
        //             books.set(j,temp);
        //         }
        //     }
        // }
        for(int i=0;i<n-1;i++){
            if(books.get(i).length() == books.get(i+1).length()){
                if(sumString(books.get(i)) > sumString(books.get(i+1))){
                    String temp = books.get(i);
                    books.set(i,books.get(i+1));
                    books.set(i+1,temp);
                }
            }
        }

        for(int i=0;i<n;i++){
            System.out.println(books.get(i));
        }
    }

    public static int sumString(String book){

        int sum =0;
        for(char ch:book.toCharArray()){
            if(Character.isDigit(ch)){
                sum += Character.getNumericValue(ch);
            }
        }
        return sum;

    }
}
