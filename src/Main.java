import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.*;



public class Main {
    public static int NextIntFromString(String str)
    {
        int seekedInt = 0;
        String convertToInt = "";
        while (true) {
            try{
                Integer.parseInt(str.substring(seekedInt, seekedInt + 1));
                convertToInt += str.substring(seekedInt, seekedInt + 1);
                seekedInt++;
            } catch (NumberFormatException e) {
                break;
            }
        }
        return Integer.parseInt(convertToInt);
    }
    public static void Day1() throws FileNotFoundException {
        int[] entries  = InputGetter.GetIntInputs();
        for(int num : entries)
           for (int num2 : entries)
               for (int num3 :  entries)
                   if (num + num2 + num3 == 2020) {
                       System.out.println(num * num2 * num3 + " " + num + " " + num2 + " " + num3);
                       break;
                    }
    }
    public static void Day2() throws FileNotFoundException {
        String[] json = InputGetter.GetStringInputs();
        int validInputs = 0;
        for (String lines : json)
        {
            int seekedInt = 0;
            String convertToInt = "";
            while (true) {
                try{
                    Integer.parseInt(lines.substring(seekedInt, seekedInt + 1));
                    convertToInt += lines.substring(seekedInt, seekedInt + 1);
                    seekedInt++;
                } catch (NumberFormatException e) {
                    break;
                }
            }
            int min = Integer.parseInt(convertToInt);
            seekedInt++;
            convertToInt = "";
            while (true) {
                try{
                    Integer.parseInt(lines.substring(seekedInt, seekedInt + 1));
                    convertToInt += lines .substring(seekedInt, seekedInt + 1);
                    seekedInt++;
                } catch (NumberFormatException e) {
                    break;
                }
            }
            int max = Integer.parseInt(convertToInt);
            char includedChar = lines.charAt(lines.indexOf(":") - 1);
            int includedTimes = 0;
            String newStr = lines.substring(lines.indexOf(":") + 1);
            for (int i = 0; i < newStr.length(); i++)
            {
                if (newStr.charAt(i) == includedChar)
                    includedTimes++;
                if (includedTimes > max)
                    break;
            }
            if ((newStr.charAt(min) == includedChar) ^ (newStr.charAt(max) == includedChar))
            //if (includedTimes >= min && includedTimes <= max)
            {
                        validInputs++;
                        /*System.out.println(newStr.charAt(min + 1) + " " + newStr.charAt(max - 1));
                        System.out.println(lines);
                        System.out.println("Min: " + min + " Max: " + max + " Char: " + includedChar);
                        System.out.println(includedTimes + " is inlcuded times");
                        System.out.println("Raising valid inputs\n");*/
            }
        }
        System.out.println(validInputs);
    }
    public static void Day3() throws FileNotFoundException {
        String[] inputs = InputGetter.GetStringInputs();
        int rightAmount = 0;
        int treeAmount = 0;
        boolean cont = false;
        for (String str : inputs)
        {
            if (cont)
            {
                cont = !cont;
                System.out.println(str);
                System.out.println(str.substring(0, rightAmount) + "O" + str.substring(rightAmount + 1));
                System.out.println("Skipped");
                System.out.println();
                continue;
            }
            if (str.charAt(rightAmount) == '#') {
                System.out.println(str);
                treeAmount++;
            }
            System.out.println(str.substring(0, rightAmount) + "O" + str.substring(rightAmount + 1));
            rightAmount += 1;
            if (rightAmount > 30)
                rightAmount -= 31;
            System.out.println();
            cont = true;
        }
        int right1 =  GetTrees(1, inputs);
        int right3 =  GetTrees(3, inputs);
        int right5 =  GetTrees(5, inputs);
        int right7 =  GetTrees(7, inputs);
        System.out.println("down 2: " + treeAmount);
        System.out.println("right 1: " + right1);
        System.out.println("right 3: " + right3);
        System.out.println("right 5: " + right5);
        System.out.println("right 7: " + right7);
        long output = treeAmount * right1; output *= right3 * right5     * right7;
        System.out.println(output);
    }
    private static int GetTrees(int rightIncrement, String[] inputs) throws FileNotFoundException {
        int rightAmount = 0;
        int treeAmount = 0;
        for (String str : inputs)
        {
            if (str.charAt(rightAmount) == '#') {
                //System.out.println(str);
                treeAmount++;
            }
            //System.out.println(str.substring(0, rightAmount) + "O" + str.substring(rightAmount + 1));
            rightAmount += rightIncrement;
            if (rightAmount > 30)
                rightAmount -= 31;
            //System.out.println();
        }
        return treeAmount;
    }
    public static void Day4() throws FileNotFoundException {
        String[] inputs = InputGetter.GetStringInputs();
        int increment = 1;
        int validInputs = 0;
        for (int i = 0; i < inputs.length; i+= increment)
        {
            String str = inputs[i];
            increment = 1;
            while(inputs.length > increment + i && inputs[i + increment].length() != 0)
            {
                str += " " + inputs[i + increment];
                increment++;
            }
            if (!(!str.contains("byr") || !str.contains("iyr") || !str.contains("eyr") || !str.contains("hgt") || !str.contains("hcl") || !str.contains("ecl") || !str.contains("pid"))) {
                //System.out.println(str);
                int byr = ParseBigInt(str.substring(str.indexOf("byr:")));
                int iyr = ParseBigInt(str.substring(str.indexOf("iyr:")));
                int eyr = ParseBigInt(str.substring(str.indexOf("eyr:")));
                int hgt = ParseBigInt(str.substring(str.indexOf("hgt:")));
                boolean cm = str.contains("cm");
                boolean validPID = false;
                try{
                    String lines = str;
                    int seekedInt = 0;
                    lines = str.substring(str.indexOf("pid:") + 4);
                    String convertToInt = "";
                    while (true) {
                        try{
                            try {
                                Integer.parseInt(lines.substring(seekedInt, seekedInt + 1));
                                convertToInt += lines.substring(seekedInt, seekedInt + 1);
                            } catch (StringIndexOutOfBoundsException e) {
                                break;
                            }
                            seekedInt++;
                        } catch (NumberFormatException e) {
                            break;
                        }
                    }
                    if (convertToInt.length() == 9) {
                        // System.out.println("Valid PID: " + convertToInt);
                        validPID = true;
                    }
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    //System.out.println(str);}
                }
                boolean ecl = str.contains("amb") || str.contains("blu") || str.contains("brn") || str.contains("gry") || str.contains("grn") || str.contains("hzl") || str.contains("oth");
                boolean hc = false;
                int j;
                for (j = 0; j < 7; j++)
                {
                    str += " ";
                    if (str.indexOf("hcl:") + j + 4 >= str.length())
                        break;
                    if (str.charAt(str.indexOf("hcl:") + 4 + j) == ' ')
                        break;
                }
                if (str.charAt(str.indexOf("hcl:") + 4) == '#') {
                    //System.out.println("Valid hcl: " + str.substring((str.indexOf("hcl:") + 4), (str.indexOf("hcl:") + 12)));
                        hc = true;
                    }

                boolean goodHgt = false;
                if (cm)
                    goodHgt = hgt >= 150 && hgt <= 193;
                else
                    goodHgt = hgt >= 59 && hgt <= 76;

                if (byr >= 1920 && byr <= 2002)
                    if (iyr >= 2010 && iyr <= 2020)
                        if (eyr >= 2020 && eyr <= 2030)
                            if (validPID)
                                if (ecl && hc && goodHgt) {
                                    validInputs++;
                                    //System.out.println(str);
                                    //System.out.println("byr: " + byr);
                                    //System.out.println("iyr: " + iyr);
                                    //System.out.println("eyr: " + eyr);
                                    //System.out.println("ecl: " + ecl);
                                    //System.out.println("hcl: " + hc);
                                    //System.out.println("hgt: " + goodHgt);
                                    //System.out.println("pid: " + validPID);
                                    //System.out.println();
                                }
            }
                //System.out.println(str + " isnt valid");
        }
        System.out.println(validInputs);
    }
    private static int ParseBigInt(String lines) throws NumberFormatException, StringIndexOutOfBoundsException
    {
        int seekedInt = 0;
        //if (lines.length() != 4) {
          //  lines = lines.substring(4);
        //}
        String convertToInt = "";
        while (true) {
            try{
                try {
                    Integer.parseInt(lines.substring(seekedInt, seekedInt + 1));
                    convertToInt += lines.substring(seekedInt, seekedInt + 1);
                } catch (StringIndexOutOfBoundsException e) {
                    break;
                }
                seekedInt++;
            } catch (NumberFormatException e) {
                break;
            }
        }
        if (convertToInt.trim().isBlank())
            return -1;
        return Integer.parseInt(convertToInt);
    }
    public static void Day5() throws FileNotFoundException {
        String[] inputs = InputGetter.GetStringInputs();
        int maxID = 0;
        List<Integer> allIds = new ArrayList<Integer>();
        for (var line : inputs)
        {
            System.out.println(line);
            int lowerLimit = 0;
            int upperLimit = 127;
            for (var i = 0; i < 6; i ++)
            {
                if (line.charAt(i) == 'B')
                {
                    lowerLimit = upperLimit - (upperLimit - lowerLimit)/2;
                    System.out.println("LowerLimit: " + lowerLimit);
                }
                else
                {
                    upperLimit = lowerLimit + (upperLimit- lowerLimit)/2;
                    System.out.println("Upperlimit: " + upperLimit);
                }
            }
            int row;
            char atChar;
            if (line.charAt(6) == 'F') {
                row = lowerLimit;
                atChar = 'F';
            }
            else {
                row = upperLimit;
                atChar = 'B';
            }
            System.out.println("Lowerlimit: " + lowerLimit + " Upperlimit: " + upperLimit + " Row: " + row + " At Char: " + atChar);
            upperLimit = 7;
            lowerLimit = 0;
            for (var i = 7; i <= 8; i ++)
            {
                if (line.charAt(i) == 'R')
                {
                    lowerLimit = upperLimit - (upperLimit - lowerLimit)/2;
                    System.out.println("LowerLimit: " + lowerLimit);
                }
                else
                {
                    upperLimit = lowerLimit + (upperLimit- lowerLimit)/2;
                    System.out.println("Upperlimit: " + upperLimit);
                }
            }
            int collum = 0;
            if (line.charAt(9) == 'R')
            {
                collum = upperLimit;
                atChar = 'R';
            }
            else {
                collum = lowerLimit;
                atChar = 'L';
            }
            System.out.println("Lowerlimit: " + lowerLimit + " Upperlimit: " + upperLimit + " Collum: " + collum + " At Char: " + atChar);
            int id = row * 8 + collum;
            if (id > maxID)
                maxID = id;
            System.out.println();
            allIds.add(id);
        }
        int [] outputs = allIds.stream().mapToInt(i->i).toArray();
        System.out.println(allIds.size());
        Collections.sort(allIds);
        int prev = 0;
        for (int i = 0; i < allIds.size(); i++) {
            if (prev == 0) {
                prev = allIds.get(i);
                continue;
            }
            if (allIds.get(i) - prev != 1) {
                System.out.println(allIds.get(i));
            }
            prev = allIds.get(i);

        }

        // System.out.println(maxID);
    }
    public static void Day6() throws FileNotFoundException {
        String[] inputs = InputGetter.GetStringInputs();
        int numofYes = 0;
        int increment = 0;
        for (int i = 0; i < inputs.length; i += increment) {
            String str = inputs[i];
            increment = 1;
            while (inputs.length > increment + i && !inputs[i + increment].isEmpty()) {
                str += " " + inputs[i + increment];
                increment++;
            }
            String dudes = "";
            //System.out.println(str);
            for (var j = 0; j < str.length(); j++)
                if (!dudes.contains(str.substring(j, j+ 1)) && !str.substring(j, j+ 1).equals(" "))
                {
                    dudes += str.substring(j, j + 1);
                    //numofYes++;
                }
            dudes = dudes.trim();
            String spaceString = "";
                int numOfChars = 0;
                str = str.trim();
                int looped = 0;
                str += " ";
            while (str.substring(1).indexOf(" ") != -1) {
                /*looped++;
                //System.out.println("Looped " + looped);
                numOfChars = 0;
                String dudes2 = "";
                //System.out.println(str + " has a space in it.");
                str = str.trim();
                if (str.indexOf(" ") == -1)
                    break;
                spaceString = str.substring(0, str.indexOf(" "));
                spaceString = spaceString.trim();
                System.out.println("String: " + str + "\nSpaceString: " + spaceString);
                //System.out.println("indexof " + str.substring(1).indexOf(" "));
               // System.out.println(str);
                for (var j = 0; j < spaceString.length(); j++)
                {
                    if (dudes.contains(Character.toString(spaceString.charAt(j))) && !dudes2.contains(Character.toString(spaceString.charAt(j)))) {
                        numOfChars++;
                        dudes2 += Character.toString(spaceString.charAt(j));
                    }
                }
                dudes2 = dudes2.trim();
                System.out.println("Dudes: " + dudes + "\nDudes2: " + dudes2);
                if (dudes2.trim().length() == dudes.trim().length()) {
                    numofYes++;
                    System.out.println(spaceString + " " + dudes2 + " " + dudes + " added");
                }
                else
                {
                 //   System.out.println(spaceString + " " + dudes2 + " " + dudes);
                }
                str = str.substring(str.indexOf(" ") + 1);
            }
            String dudes2 = "";
            System.out.println("String: " + str);

            for (var j = 0; j < str.length(); j++)
            {
                if (dudes.contains(Character.toString(str.charAt(j))) && !dudes2.contains(Character.toString(str.charAt(j)))) {
                    numOfChars++;
                    dudes2 += Character.toString(str.charAt(j));
                }
            }
            dudes2 = dudes2.trim();
            if (dudes2.trim().length() == dudes.trim().length()) {
                numofYes++;
                System.out.println(str + " " + dudes2 + " " + dudes + " added");
            }
            System.out.println();*/
            }

        }
        System.out.print(numofYes);
    }
    public static void Day7() throws FileNotFoundException {
        String[] inputs = InputGetter.GetStringInputs();
        ArrayList<Bag> allBags = new ArrayList<Bag>();
        System.out.println(RecursiveGetBags("shiny gold", inputs));


        /*for (var i = 0; i < 11; i++) {
            ArrayList<String> bags2 = new ArrayList<>();
            for (String line : inputs) {
                for (String bag : bags) {
                    boolean flag = false;
                    if (line.contains(bag)) {
                        for (String baj : bags2) {
                            if (flag)
                                break;
                            if (baj.equals(line.substring(0, line.indexOf("bag"))))
                                flag = true;
                        }
                        for (String baj : bags) {
                            if (flag)
                                break;
                            if (baj.equals(line.substring(0, line.indexOf("bag"))))
                                flag = true;
                        }
                        if (flag)
                            continue;
                        bags2.add(line.substring(0, line.indexOf("bag")));
                    }
                }
            }
            for (String bag : bags2)
                bags.add(bag);
        }
        for (String bag : bags)
            System.out.println(bag);

        System.out.println(bags.size() - 1);*/
    }
    private static int RecursiveGetBags(String bagName, String[] outputs)
    {
        int result = 0;

        if (bagName.equals("other") || bagName.equals("no other bags."))
            return 1;
        System.out.println(bagName + " is the name");
        for (String line : outputs)
        {
            if (line.contains(bagName + " bags contain"))
            {
                line = line.substring(line.indexOf("bags contain ") + 13);
                System.out.println(line + " is the line");
                if (line.equals("no other bags."))
                    return 0;
                while (line.indexOf(",") != -1)
                {
                    int multyby = ParseBigInt(line.trim());
                    String bagname = line.substring(2, line.indexOf("bag")).trim();
                    line = line.substring(line.indexOf(",") + 1);
                    System.out.println(line + " is the line without A comma");
                    System.out.println(multyby + " is multyby");
                    int prevResult = result;
                    result += multyby + (multyby * RecursiveGetBags(bagname, outputs));
                    System.out.println("Result is now " + result + " was " + prevResult + " for " + bagname + " in " + bagName);
                    System.out.println(line.substring(line.indexOf(" ") + 1, line.indexOf("bag")).trim());
                }
                line = line.trim();
                System.out.println(line + " is the period line");
                line = line.substring(0, line.indexOf("."));
                int multyby = ParseBigInt(line.trim());
                System.out.println(multyby + " is multyby");
                result += multyby + (multyby * RecursiveGetBags(line.substring(line.indexOf(" ") + 1, line.indexOf(" bag")), outputs));
                System.out.println("Result for " + bagName + " is " + result);
                return result;
            }
        }
        return 0;
    }
    public static void Day8() throws FileNotFoundException{
        String[] inputs = InputGetter.GetStringInputs();
        int acc = 0;
        int jmp = 0;
        int j = 0;
        ArrayList<Integer> visitedLines;
        int repeatLinesVisited = 1000;
        boolean jmpActive = false;
        String line = "";
        String lastLine = "acc +37";
        for (int l = 1; l < inputs.length; l++) {
            if (!inputs[l-1].contains("acc"))
            System.out.println("Changed " + inputs[l - 1] + " back to " + lastLine + " l - 1: " + (l-1));
            inputs[l - 1] = lastLine;
            String inputLine = inputs[l];
            lastLine = inputLine;
            if (inputLine.equals("nop +9292") || repeatLinesVisited < 101)
                break;
            if (inputLine.contains("acc"))
                continue;
            else if (inputLine.contains("nop"))
                inputs[l] = "jmp" + inputLine.substring(3);
            else
                inputs[l] = "nop" + inputLine.substring(3);
            System.out.println("Changed " + inputLine + " to " + inputs[l] + " l: " + l);
            acc = 0;
            repeatLinesVisited = 0;
            jmp = 1;
            visitedLines = new ArrayList<>();
            for (int i = 0; i < inputs.length; i += jmp) {
                jmp = 1;
                j++;
                if (visitedLines.contains(i)) {
                    repeatLinesVisited++;
                    if (repeatLinesVisited > 100) {
                        System.out.println("Broke, visited lines: " + repeatLinesVisited);
                        break;
                    }
                }
                line = inputs[i];
                if (line.contains("acc")) {
                    if (line.charAt(4) == '+') {
                        acc += ParseBigInt(line.substring(5));
                    } else
                        acc -= ParseBigInt(line.substring(5));
                } else if (line.contains("jmp")) {
                    jmpActive = true;
                    if (line.charAt(4) == '+') {
                        jmp = ParseBigInt(line.substring(5));
                    } else
                        jmp = -1 * ParseBigInt(line.substring(5));
                }
                visitedLines.add(i);
                 System.out.println(line + " i: " + i + " acc: " + acc);
            }
        }
        System.out.println(acc);
    }
    public static void Day9() throws FileNotFoundException {
        long[] inputs = InputGetter.GetLongInputs();
        long smallest = inputs[0];
        long largest = 0;
        long sum = 0;
        int tries = 1;
        while (sum != 41682220 && tries < inputs.length) {
            sum = 0;
            largest = 0;
            smallest = inputs[tries];
            for (int i = tries; i < inputs.length; i++) {
                sum += inputs[i];
                if (inputs[i] > largest)
                    largest = inputs[i];
                if (inputs[i] < smallest)
                    smallest = inputs[i];
                System.out.println("Sum: " + sum + " i: " + inputs[i] + " biggest:" + largest);
                if (sum == 41682220)
                    break;
            }
            tries++;
            System.out.println("\nMoving to try: " + tries);
        }
        System.out.println((smallest + largest) + " sum: " + sum + " tries: " + tries + " biggest: " + largest + " smallest: " + smallest);
        /*List<Long> allInts = new ArrayList<>();
        for (long num : inputs)
        {
            for (long num2 : inputs)
                allInts.add(num + num2);
        }
        for (long i : inputs )
        {
            if (!allInts.contains(i))
                System.out.println(i);
        }*/
    }
    public static void Day10() throws FileNotFoundException {
        long[] inputs = InputGetter.GetLongInputs();
        long max = 0;
        for (long num : inputs) {
            if (num > max)
                max = num;
        }
        System.out.println(max);
        int diff1 = 0;
        int diff3 = 0;
        long lastnum = 0;
        int minNum = 0;
        Arrays.sort(inputs);
        List<Long> validInputs = new ArrayList<>();
        for (var i = 0; i < inputs.length; i++) {
            for (long nums : inputs) {
                //System.out.println("num: " + nums);
                if (nums - lastnum == 1) {
                    diff1++;
                    System.out.println("Lastnum: " + lastnum + " num: " + nums + " diff 1");
                    lastnum = nums;
                    i = 0;
                    validInputs.add(lastnum);
                    break;
                }
                else if (nums - lastnum == 2) {
                    System.out.println("Lastnum: " + lastnum + " num: " + nums + " diff 2");
                    lastnum = nums;
                    i = 0;
                    validInputs.add(lastnum);
                    break;
                }
                else if (nums - lastnum == 3) {
                    diff3++;
                    System.out.println("Lastnum: " + lastnum + " num: " + nums + " diff 3");
                    lastnum = nums;
                    i = 0;
                    validInputs.add(lastnum);
                    break;
                }
            }
        }

        //System.out.println((diff3 * diff1) + " diff3: " + diff3 + " diff1: " + diff1);

        for (var i = 0; i < inputs.length; i++) {
            for (long nums : inputs) {
                //System.out.println("num: " + nums);
                if (nums - lastnum == 1) {
                    diff1++;
                    System.out.println("Lastnum: " + lastnum + " num: " + nums + " diff 1");
                    lastnum = nums;
                    i = 0;
                    validInputs.add(lastnum);
                    break;
                }
                else if (nums - lastnum == 3) {
                    System.out.println("Lastnum: " + lastnum + " num: " + nums + " diff 2");
                    lastnum = nums;
                    i = 0;
                    validInputs.add(lastnum);
                    break;
                }
                else if (nums - lastnum == 2) {
                    diff3++;
                    System.out.println("Lastnum: " + lastnum + " num: " + nums + " diff 3");
                    lastnum = nums;
                    i = 0;
                    validInputs.add(lastnum);
                    break;
                }
            }
        }
        for (var i = 0; i < inputs.length; i++) {
            for (long nums : inputs) {
                //System.out.println("num: " + nums);
                if (nums - lastnum == 3) {
                    diff1++;
                    System.out.println("Lastnum: " + lastnum + " num: " + nums + " diff 1");
                    lastnum = nums;
                    i = 0;
                    validInputs.add(lastnum);
                    break;
                }
                else if (nums - lastnum == 2) {
                    System.out.println("Lastnum: " + lastnum + " num: " + nums + " diff 2");
                    lastnum = nums;
                    i = 0;
                    validInputs.add(lastnum);
                    break;
                }
                else if (nums - lastnum == 1) {
                    diff3++;
                    System.out.println("Lastnum: " + lastnum + " num: " + nums + " diff 3");
                    lastnum = nums;
                    i = 0;
                    validInputs.add(lastnum);
                    break;
                }
            }
        }
        for (var i = 0; i < inputs.length; i++) {
            for (long nums : inputs) {
                //System.out.println("num: " + nums);
                if (nums - lastnum == 2) {
                    diff1++;
                    System.out.println("Lastnum: " + lastnum + " num: " + nums + " diff 1");
                    lastnum = nums;
                    i = 0;
                    validInputs.add(lastnum);
                    break;
                }
                else if (nums - lastnum == 1) {
                    System.out.println("Lastnum: " + lastnum + " num: " + nums + " diff 2");
                    lastnum = nums;
                    i = 0;
                    validInputs.add(lastnum);
                    break;
                }
                else if (nums - lastnum == 3) {
                    diff3++;
                    System.out.println("Lastnum: " + lastnum + " num: " + nums + " diff 3");
                    lastnum = nums;
                    i = 0;
                    validInputs.add(lastnum);
                    break;
                }
            }
        }
        for (var i = 0; i < inputs.length; i++) {
            for (long nums : inputs) {
                //System.out.println("num: " + nums);
                if (nums - lastnum == 3) {
                    diff1++;
                    System.out.println("Lastnum: " + lastnum + " num: " + nums + " diff 1");
                    lastnum = nums;
                    i = 0;
                    validInputs.add(lastnum);
                    break;
                }
                else if (nums - lastnum == 1) {
                    System.out.println("Lastnum: " + lastnum + " num: " + nums + " diff 2");
                    lastnum = nums;
                    i = 0;
                    validInputs.add(lastnum);
                    break;
                }
                else if (nums - lastnum == 2) {
                    diff3++;
                    System.out.println("Lastnum: " + lastnum + " num: " + nums + " diff 3");
                    lastnum = nums;
                    i = 0;
                    validInputs.add(lastnum);
                    break;
                }
            }
        }
        for (var i = 0; i < inputs.length; i++) {
            for (long nums : inputs) {
                //System.out.println("num: " + nums);
                if (nums - lastnum == 2) {
                    diff1++;
                    System.out.println("Lastnum: " + lastnum + " num: " + nums + " diff 1");
                    lastnum = nums;
                    i = 0;
                    validInputs.add(lastnum);
                    break;
                }
                else if (nums - lastnum == 3) {
                    System.out.println("Lastnum: " + lastnum + " num: " + nums + " diff 2");
                    lastnum = nums;
                    i = 0;
                    validInputs.add(lastnum);
                    break;
                }
                else if (nums - lastnum == 1) {
                    diff3++;
                    System.out.println("Lastnum: " + lastnum + " num: " + nums + " diff 3");
                    lastnum = nums;
                    i = 0;
                    validInputs.add(lastnum);
                    break;
                }
            }
        }

        long[] newValidInputs = validInputs.stream().mapToLong(i->i).toArray();
        Arrays.sort(newValidInputs);
        for (long num : newValidInputs)
            System.out.println(num + " valid");
        int GetNext = 3;
        List<Long> allSums = new ArrayList<>();
        System.out.println(newValidInputs.length);


    }
    public static void Day11() throws FileNotFoundException{
        String[] inputs = InputGetter.GetStringInputs();
        int lastStrHash = 0;
        for (var l = 0; l < 9999; l++) {
            List<Integer> flipJChars = new ArrayList<>();
            List<Integer> flipIChars = new ArrayList<>();
            for (int i = 0; i < inputs.length; i++) {
                for (int j = 0; j < inputs[i].length(); j++) {
                    int adjectedOcupied = 0;
                    if (inputs[i].charAt(j) == '.')
                        continue;
                    else if (inputs[i].charAt(j) == '#') {
                        if (j != 0) {
                            if (inputs[i].charAt(j - 1) == '#')
                            {
                                int temp = j;
                                int temp2 = i;
                                while (temp != -1 && temp2 != -1) {
                                    temp--;
                                    if (inputs[temp2].charAt(temp) == '.')
                                        continue;
                                    else if (inputs[temp2].charAt(temp) == '#') {
                                        adjectedOcupied++;
                                        break;
                                    }
                                }
                            }
                        }
                        if (j != inputs[i].length() - 1)
                            if (inputs[i].charAt(j + 1) == '#')
                                adjectedOcupied++;
                        if (i != 0) {
                            if (j != 0)
                            {
                                int temp = j;
                                int temp2 = i;
                                while (temp != -1 && temp2 != -1) {
                                    temp--;
                                    temp2--;
                                    if (inputs[temp2].charAt(temp) == '.')
                                        continue;
                                    else if (inputs[temp2].charAt(temp) == '#') {
                                        adjectedOcupied++;
                                        break;
                                    }
                                }
                            }

                            if (j != inputs[i].length() - 1)
                                if (inputs[i - 1].charAt(j + 1) == '#')
                                    adjectedOcupied++;
                            if (inputs[i - 1].charAt(j) == '#')
                                adjectedOcupied++;
                        }
                        if (i != inputs.length - 1) {
                            if (j != 0)
                            {
                                int temp = j;
                                int temp2 = i;
                                while (temp != -1 && temp2 != -1) {
                                    temp--;
                                    temp2++;
                                    if (inputs[temp2].charAt(temp) == '.')
                                        continue;
                                    else if (inputs[temp2].charAt(temp) == '#') {
                                        adjectedOcupied++;
                                        break;
                                    }
                                }
                            }
                            if (j != inputs[i].length() - 1)
                            {
                                int temp = j;
                                int temp2 = i;
                                while (temp != -1 && temp2 != -1) {
                                    temp++;
                                    temp2++;
                                    if (inputs[temp2].charAt(temp) == '.')
                                        continue;
                                    else if (inputs[temp2].charAt(temp) == '#') {
                                        adjectedOcupied++;
                                        break;
                                    }
                                }
                            }
                            if (inputs[i + 1].charAt(j) == '#')
                            {
                                int temp = j;
                                int temp2 = i;
                                while (temp != -1 && temp2 != -1) {
                                    temp2++;
                                    if (inputs[temp2].charAt(temp) == '.')
                                        continue;
                                    else if (inputs[temp2].charAt(temp) == '#') {
                                        adjectedOcupied++;
                                        break;
                                    }
                                }
                            }
                        }
                        if (adjectedOcupied >= 5) {
                            flipJChars.add(j);
                            flipIChars.add(i);
                        }
                    } else {
                        if (j != 0) {
                            if (inputs[i].charAt(j - 1) == '#')
                                adjectedOcupied++;
                        }
                        if (j != inputs[i].length() - 1)
                            if (inputs[i].charAt(j + 1) == '#')
                                adjectedOcupied++;
                        if (i != 0) {
                            if (j != 0)
                            {
                                int temp = j;
                                int temp2 = i;
                                while (temp != -1 && temp2 != -1) {
                                    temp--;
                                    temp2--;
                                    if (inputs[temp2].charAt(temp) == '.')
                                        continue;
                                    else if (inputs[temp2].charAt(temp) == '#') {
                                        adjectedOcupied++;
                                        break;
                                    }
                                }
                            }

                            if (j != inputs[i].length() - 1)
                                if (inputs[i - 1].charAt(j + 1) == '#')
                                    adjectedOcupied++;
                            if (inputs[i - 1].charAt(j) == '#')
                                adjectedOcupied++;
                        }
                        if (i != inputs.length - 1) {
                            if (j != 0)
                            {
                                int temp = j;
                                int temp2 = i;
                                while (temp != -1 && temp2 != -1) {
                                    temp--;
                                    temp2++;
                                    if (inputs[temp2].charAt(temp) == '.')
                                        continue;
                                    else if (inputs[temp2].charAt(temp) == '#') {
                                        adjectedOcupied++;
                                        break;
                                    }
                                }
                            }
                            if (j != inputs[i].length() - 1)
                            {
                                int temp = j;
                                int temp2 = i;
                                while (temp != -1 && temp2 != -1) {
                                    temp++;
                                    temp2++;
                                    if (inputs[temp2].charAt(temp) == '.')
                                        continue;
                                    else if (inputs[temp2].charAt(temp) == '#') {
                                        adjectedOcupied++;
                                        break;
                                    }
                                }
                            }
                            if (inputs[i + 1].charAt(j) == '#')
                            {
                                int temp = j;
                                int temp2 = i;
                                while (temp != -1 && temp2 != -1) {
                                    temp2++;
                                    if (inputs[temp2].charAt(temp) == '.')
                                        continue;
                                    else if (inputs[temp2].charAt(temp) == '#') {
                                        adjectedOcupied++;
                                        break;
                                    }
                                }
                            }
                        }
                        if (adjectedOcupied >= 5) {
                            flipJChars.add(j);
                            flipIChars.add(i);
                        }
                    }
                }
            }
            int[] iChars = flipIChars.stream().mapToInt(i -> i).toArray();
            int[] jChars = flipJChars.stream().mapToInt(i -> i).toArray();
            for (var i = 0; i < iChars.length; i++) {
                if (inputs[iChars[i]].charAt(jChars[i]) == '#')
                    inputs[iChars[i]] = inputs[iChars[i]].substring(0, jChars[i]) + "L" + inputs[iChars[i]].substring(jChars[i] + 1);
                else
                    inputs[iChars[i]] = inputs[iChars[i]].substring(0, jChars[i]) + "#" + inputs[iChars[i]].substring(jChars[i] + 1);
            }
            if (Arrays.hashCode(inputs) == lastStrHash)
                break;
            lastStrHash = Arrays.hashCode(inputs);
            for (var line : inputs)
                System.out.println(line);
            System.out.println(l + 1);
        }
        int ocupied = 0;
        for (String str : inputs)
        {
            for (int i = 0; i < str.length(); i++)
            {
                if (str.charAt(i) == '#')
                    ocupied++;
            }
        }
        System.out.println(ocupied);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Day11();
    }
}
class InputGetter
{
    private static final File inputs = new File("C:\\Users\\tempe\\IdeaProjects\\Aoc Adventures\\src\\Inputs");
    private static Scanner fileReader;
    static {
        try {
            fileReader = new Scanner(inputs);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String[] GetStringInputs() throws FileNotFoundException {
        ArrayList<String> result = new ArrayList<String>();
        while (fileReader.hasNextLine())
        {
            result.add(fileReader.nextLine());
        }
        return GetStringArray(result);
    }

    public static int[] GetIntInputs() throws FileNotFoundException {
        List<Integer> result = new ArrayList<>();
        while (fileReader.hasNextLine())
        {
            result.add(Integer.parseInt(fileReader.nextLine()));
        }
        return result.stream().mapToInt(i->i).toArray();
    }
    public static long[] GetLongInputs() throws FileNotFoundException
    {
        List<Long> result = new ArrayList<>();
        while (fileReader.hasNextLine())
        {
            result.add(Long.parseLong(fileReader.nextLine()));
        }
        return result.stream().mapToLong(i->i).toArray();
    }
    public static String[] GetStringArray(ArrayList<String> arr) {

    // declaration and initialise String Array
    String str[] = new String[arr.size()];

    // ArrayList to Array Conversion
    for (int j = 0; j < arr.size(); j++) {
        // Assign each value to String array
        str[j] = arr.get(j);
    }
    return str;
    }
}
