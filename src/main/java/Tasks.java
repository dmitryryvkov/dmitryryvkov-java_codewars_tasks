import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import org.apache.commons.lang3.StringUtils;


public class Tasks {

    public static void main(String[] args) {

        isStringPalindrome("asdfdsa");
        isStringPalindrome("abibas");

        isStringPalindromeStringBuilder("asdfdsa");
        isStringPalindromeStringBuilder("alfabank");

        bubbleSorting(12, 5, 8, 0, 2, 5, 20, 15, 19, 11);

        System.out.println(findUniqueOddOrEvenElement(-3, 5, 13, 19, 4));
        System.out.println(findUniqueOddOrEvenElement(-3));

        System.out.println(findUniqueDoubleElement(1.0, 1.0, 1.0, 2.5, 1.0, 1.0));
        System.out.println(findUniqueDoubleElement(1.0, 1.0, 33.1));

        System.out.println(binarySumFromInt(12));
        System.out.println(binarySumFromInt(1234567));

        System.out.println(findIt(5, 4, 3, 2, 1, 5, 4, 3, 2, 10, 10)); //unreadable now, need to refactor

        System.out.println(songDecoder("WUBWUBABCWUB"));
        System.out.println(songDecoder("RWUBWUBWUBLWUB"));
        System.out.println(songDecoder("WUBWEWUBAREWUBWUBTHEWUBCHAMPIONSWUBMYWUBFRIENDWUB"));

        System.out.println(highAndLow("42 42"));
        System.out.println(highAndLow("8 15 233 -59 56 88 343 6 0 -2 45"));
        System.out.println(highAndLow("8"));

        System.out.println(whoLikeIt("Alex", "Jacob", "Mark", "Max"));
        System.out.println(whoLikeIt("Alex"));
        System.out.println(whoLikeIt());

        System.out.println(validatePin("123456"));
        System.out.println(validatePin("1234"));
        System.out.println(validatePin("1234567.*"));

        System.out.println(bouncingBall(3.0, 0.66, 1.5));
        System.out.println(bouncingBall(300000000000000.0, 0.66, 1.5));

        System.out.println(orderElementsByDigitsInside("is2 Thi1s T4est 3a"));
        System.out.println(orderElementsByDigitsInside("4 2 3 7 5 6 1 8 9"));
        System.out.println(orderElementsByDigitsInside(""));

        System.out.println(findOneMissingLetter('a', 'c', 'd', 'e', 'f', 'g'));
        System.out.println(findOneMissingLetter('D', 'E', 'G'));

        System.out.println(camelCase("ThisIsMyString"));
    }

    private static String camelCase(String input) {
        return input.replaceAll("([^_])([A-Z])", "$1 $2");
    }

    private static char findOneMissingLetter(char... array) {
        int missInt = 0;

        for (int i = 0; i < array.length - 1; i++) {
            missInt = (int) array[i + 1] != (int) array[i] + 1 ? (int) array[i] + 1 : missInt;
        }
        return Character.toChars(missInt)[0];

        /* char expectableLetter = array[0];
            for(char letter : array){
                if(letter != expectableLetter) break;
                expectableLetter++;
            }
        return expectableLetter;*/

        /* int index = IntStream.range(0, array.length-1).filter(i -> array[i] != array[i+1]-1).findFirst().getAsInt();
        return (char)(array[index] + 1);*/
    }

    private static String orderElementsByDigitsInside(String words) {

        String[] newList = new String[words.length()];

        if (words.length() > 0) {
            newList = words.split(" ");
            for (int i = 0; i < newList.length; i++) {
                for (int j = 0; j < newList.length - 1; j++) {
                    int firstDigit = Integer.parseInt(StringUtils.getDigits(newList[j]));
                    int secondDigit = Integer.parseInt(StringUtils.getDigits(newList[j + 1]));

                    if (firstDigit > secondDigit) {
                        String s = newList[j + 1];
                        newList[j + 1] = newList[j];
                        newList[j] = s;
                    }
                }
            }
        }

        return StringUtils.join(newList, " ");

/*        return Arrays.stream(words.split(" "))
                .sorted(Comparator.comparing(s -> Integer.valueOf(s.replaceAll("\\D", ""))))
                .reduce((a, b) -> a + " " + b).get();*/

/*        return Arrays.stream(words.split(" "))
                .sorted((a, b) -> {
                    return a.replaceAll("\\D+", "").compareTo(b.replaceAll("\\D+", ""));
                }).collect(Collectors.joining(" "));*/
    }

    private static int bouncingBall(double h, double bounce, double window) {
        int count = -1;
        double hFinal = h;

        if (h > 0 && bounce > 0 && bounce < 1 && window < h) {
            while (hFinal > window / bounce) {
                hFinal *= bounce;
                count += 2;
            }
            count += 2;
        }
        return count;

        /*

         if (h <= 0 || bounce <= 0 || bounce >= 1 || window >= h) {
        return -1;
      }
      return 2 + bouncingBall(h * bounce, bounce, window); */
    }

    private static boolean validatePin(String pin) {
        return pin.matches("[0-9]+") && (pin.length() == 4 || pin.length() == 6);
    }

    private static String whoLikeIt(String... args) {
        String[] clone = args.clone();
        String result;

        if (clone.length == 0)
            result = "no one likes this";
        else if (clone.length == 1)
            result = clone[0] + " likes this";
        else if (clone.length == 2)
            result = clone[0] + " and " + clone[1] + " like this";

        else if (clone.length == 3)
            result = clone[0] + ", " + clone[1] + " and " + clone[2] + " like this";
        else result = clone[0] + ", " + clone[1] + " and " + (clone.length - 2) + " others like this";

        return result;

        /* switch (args.length){
           case 0: return "no one likes this";
           case 1: return String.format("%s likes this", args[0]);
           case 2: return String.format("%s and %s like this", args[0], args[1]);
           case 3: return String.format("%s, %s and %s like this", args[0], args[1], args[2]);
            default: return String.format("%s, %s and %d others like this", args[0], args[1], args.length-2);
        }*/
    }

    private static String highAndLow(String s) {
        List<Integer> sortedListOfInts = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).sorted().boxed().
                collect(Collectors.toList());
        return Collections.max(sortedListOfInts) + " " + Collections.min(sortedListOfInts);

/*        IntSummaryStatistics stats = Arrays.stream(numbers.split("\\s")).
                mapToInt(Integer::parseInt).summaryStatistics();

        return String.format("%d %d", stats.getMax(), stats.getMin());*/

    }

    private static String songDecoder(String s) {
        return StringUtils.normalizeSpace(s.replaceAll("WUB", " "));
    }

    private static int findIt(int... a) {
        return Arrays.stream(a).boxed()
                .collect(groupingBy(identity(), counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() % 2 != 0)
                .findFirst()
                .map(Map.Entry::getKey).get();

    }

    private static int binarySumFromInt(int n) {
        return Arrays.stream(Integer.toBinaryString(n).split("")).mapToInt(Integer::parseInt).sum();

        //Integer.bitCount(n);
    }

    private static double findUniqueDoubleElement(double... arr) {
        double popularNumber = arr[0] == arr[1] || arr[0] == arr[2] ? arr[0] : arr[1];

        double[] unique = Arrays.stream(arr).
                sorted().
                distinct().filter(f -> f != popularNumber).toArray();

        return unique[0];

        /* return Arrays.stream(array).boxed()
                .collect(groupingBy(identity(), counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() % != 0)
                .findFirst()
                .map(Entry::getKey)
                .orElse(0.0);*/

        /* Arrays.sort(arr);
            return arr[0] == arr[1] ? arr[arr.length-1]:arr[0]; */
    }

    private static int findUniqueOddOrEvenElement(int... integers) {

        int[] odd = Arrays.stream(integers).filter(n -> n % 2 != 0).toArray();
        int[] even = Arrays.stream(integers).filter(n -> n % 2 == 0).toArray();

        return odd.length == 1 ? odd[0] : even[0];

    }

    private static void bubbleSorting(int... bubble) {
        int kk;
        for (int i = 0; i < bubble.length; i++) {
            for (int j = 0; j < bubble.length - 1; j++) {
                if (bubble[j] > bubble[j + 1]) {
                    kk = bubble[j + 1];
                    bubble[j + 1] = bubble[j];
                    bubble[j] = kk;
                }
            }
        }
        for (int j : bubble) {
            System.out.println(j);

        }
    }

    private static void isStringPalindromeStringBuilder(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        String s = sb.toString();
        System.out.println(s);
    }


    private static void isStringPalindrome(String str) {
        if (isPalindrome(str)) {
            System.out.println("Palindrome");
        } else System.out.println("No palindrome");
    }

    private static boolean isPalindrome(String palindrome) {
        int i = 0, j = palindrome.length() - 1;

        while (i < j) {

            if (palindrome.charAt(i) != palindrome.charAt(j)) {
                return false;
            }

            i++;
            j--;

        }
        return true;
    }
}
