package com.terranovita.iota;

import jota.IotaAPI;
import jota.dto.response.GetNewAddressResponse;
import jota.error.ArgumentException;
import jota.error.InvalidAddressException;
import jota.error.InvalidSecurityLevelException;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FindTypoInIOTASeed {

    public static String DONATION_WALLET_ADDRESS = "ELDM9DAWZVHNIQQROREDKPUJRZKMZCRKROSGQXROZCYSQJKKTRNHNEBKNRSAJXOUZBGKUFWTNTN9VKTBWYJFDAQNOD";
    public static String POSSIBLE_CHARS = "9ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String originalSeed;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        System.out.print("Enter your original seed: ");
        String originalSeed = scanner.next().trim();

        System.out.print("Enter a wallet address holding your funds: ");
        String addressToLookFor = scanner.next().trim();

        System.out.println("Index offset (default is 0): ");
        int indexOffset = 0;
        try {
            String indexOffsetString = scanner.next();
            indexOffset = Integer.parseInt(indexOffsetString);
        } catch (NumberFormatException ex) {
        }
        if (indexOffset < 0) {
            indexOffset = 0;
        }

        FindTypoInIOTASeed seedFinder = new FindTypoInIOTASeed();
        try {
            seedFinder.findTypoSeed(originalSeed, addressToLookFor, indexOffset);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ArgumentException e) {
            e.printStackTrace();
        } catch (InvalidSecurityLevelException e) {
            e.printStackTrace();
        } catch (InvalidAddressException e) {
            e.printStackTrace();
        }
    }

    private static String sanitizeSeed(String seed) {
        seed = seed.toUpperCase();
        String newSeed = "";
        for (int i = 0; i < seed.length(); i++) {
            if (("9ABCDEFGHIJKLMNOPQRSTUVWXYZ").indexOf(seed.charAt(i)) < 0) {
                newSeed += "9";
            } else {
                newSeed += seed.charAt(i);
            }
        }
        newSeed = newSeed.length() < 81 ? newSeed + StringUtils.repeat('9', 81 - newSeed.length()) : newSeed;
        return newSeed;
    }


    private List<String> addSeedsWithCopyPastError(List<String> similarSeeds) {
        System.out.println("Generating seeds with missing letters in front/back");
        similarSeeds.add(sanitizeSeed(originalSeed.substring(0, originalSeed.length() - 1)));
        similarSeeds.add(sanitizeSeed(originalSeed.substring(0, originalSeed.length() - 2)));
        similarSeeds.add(sanitizeSeed(originalSeed.substring(0, originalSeed.length() - 3)));
        similarSeeds.add(sanitizeSeed(originalSeed.substring(0, originalSeed.length() - 4)));
        similarSeeds.add(sanitizeSeed(originalSeed.substring(1, originalSeed.length() - 1)));
        similarSeeds.add(sanitizeSeed(originalSeed.substring(1, originalSeed.length() - 2)));
        similarSeeds.add(sanitizeSeed(originalSeed.substring(1, originalSeed.length() - 3)));
        similarSeeds.add(sanitizeSeed(originalSeed.substring(1, originalSeed.length() - 4)));
        similarSeeds.add(sanitizeSeed(originalSeed.substring(2, originalSeed.length() - 1)));
        similarSeeds.add(sanitizeSeed(originalSeed.substring(2, originalSeed.length() - 2)));
        similarSeeds.add(sanitizeSeed(originalSeed.substring(2, originalSeed.length() - 3)));
        similarSeeds.add(sanitizeSeed(originalSeed.substring(2, originalSeed.length() - 4)));
        similarSeeds.add(sanitizeSeed(originalSeed.substring(3, originalSeed.length() - 1)));
        similarSeeds.add(sanitizeSeed(originalSeed.substring(3, originalSeed.length() - 2)));
        similarSeeds.add(sanitizeSeed(originalSeed.substring(3, originalSeed.length() - 3)));
        similarSeeds.add(sanitizeSeed(originalSeed.substring(3, originalSeed.length() - 4)));
        similarSeeds.add(sanitizeSeed(originalSeed.substring(4, originalSeed.length() - 1)));
        similarSeeds.add(sanitizeSeed(originalSeed.substring(4, originalSeed.length() - 2)));
        similarSeeds.add(sanitizeSeed(originalSeed.substring(4, originalSeed.length() - 3)));
        similarSeeds.add(sanitizeSeed(originalSeed.substring(4, originalSeed.length() - 4)));
        return similarSeeds;
    }

    private List<String> addSeedsWithRemovedCharacter1(List<String> similarSeeds) {
        System.out.println("Generating seeds with one character missing");
        for (int x = originalSeed.length() - 2; x > 0; x--) {
            String newSeed = originalSeed.substring(0, x - 1) + originalSeed.substring(x, originalSeed.length()) + "9";
            if (!similarSeeds.contains(newSeed)) {
                similarSeeds.add(newSeed);
            }
        }
        return similarSeeds;
    }

    private List<String> addSeedsWithDoubleCharacter1(List<String> similarSeeds) {
        System.out.println("Generating seeds with character typed double");
        for (int x = originalSeed.length() - 2; x > 0; x--) {
            String newSeed = originalSeed.substring(0, x) + originalSeed.substring(x - 1, x) + originalSeed.substring(x, originalSeed.length() - 1);
            if (!similarSeeds.contains(newSeed)) {
                similarSeeds.add(newSeed);
            }
        }
        return similarSeeds;
    }


    private List<String> addSeedsWithReplacingChars(List<String> similarSeeds) {
        System.out.println("Generating seeds with letters that look like each other replaced");
        similarSeeds.add(originalSeed.replace('U', 'V'));
        similarSeeds.add(originalSeed.replace('V', 'U'));
        similarSeeds.add(originalSeed.replace('9', 'G'));
        similarSeeds.add(originalSeed.replace('I', 'L'));
        similarSeeds.add(originalSeed.replace('L', 'I'));
        similarSeeds.add(originalSeed.replace('D', 'O'));
        similarSeeds.add(originalSeed.replace('O', 'D'));
        return similarSeeds;
    }

    private List<String> addSeedsWithDoubleCharacter2(List<String> similarSeeds) {
        System.out.println("Generating seeds with block of 2 characters retyped");
        for (int x = originalSeed.length() - 2; x > 1; x--) {
            String newSeed = originalSeed.substring(0, x) + originalSeed.substring(x - 2, x) + originalSeed.substring(x, originalSeed.length() - 2);
            if (!similarSeeds.contains(newSeed)) {
                similarSeeds.add(newSeed);
            }
        }
        return similarSeeds;
    }

    private List<String> addSeedsWithListOfDyslexiaSeeds(List<String> similarSeeds) {
        System.out.println("Generating seeds with 2 neighbouring characters switched");
        for (int x = originalSeed.length() - 2; x > 1; x--) {
            String newSeed = originalSeed.substring(0, x - 2) + originalSeed.substring(x - 1, x) + originalSeed.substring(x - 2, x - 1) + originalSeed.substring(x);
            if (!similarSeeds.contains(newSeed)) {
                similarSeeds.add(newSeed);
            }
        }
        return similarSeeds;
    }

    private List<String> addSeedsWithMissingRandomCharacter(List<String> similarSeeds) {
        for (int x = originalSeed.length() - 1; x >= 0; x--) {
            for (int y = 0; y < POSSIBLE_CHARS.length(); y++) {
                String newSeed = originalSeed.substring(0, x) + POSSIBLE_CHARS.substring(y, y + 1) + originalSeed.substring(x, originalSeed.length() - 1);
                if (!similarSeeds.contains(newSeed)) {
                    similarSeeds.add(newSeed);
                }
            }
        }
        return similarSeeds;
    }

    private List<String> addSeedsWithSimilarSeeds1Char(List<String> similarSeeds) {
        System.out.println("Generating seeds with 1 character mistyped");
        return innerAddSeedsWithListOfSimilarSeeds1Char(similarSeeds, this.originalSeed);
    }

    private List<String> innerAddSeedsWithListOfSimilarSeeds1Char(List<String> similarSeeds, String theSeed) {
        for (int x = theSeed.length() - 3; x > 0; x--) {
            for (int y = 0; y < POSSIBLE_CHARS.length(); y++) {
                String newSeed = theSeed.substring(0, x) + POSSIBLE_CHARS.substring(y, y + 1) + theSeed.substring(x + 1);
                if (!similarSeeds.contains(newSeed)) {
                    similarSeeds.add(newSeed);
                }
            }
        }
        return similarSeeds;
    }


    // This takes too long and is not recommended. Contact me at iota@terranovita.com if you need customized help. I'll see what I can do for you.
    private List<String> addSeedsWithListOfSimilarSeeds2Chars(List<String> similarSeeds) {
        System.out.println("Generating seeds with 2 characters mistyped (CAUTION: This will take a long time, not recommended)");
        for (int x = originalSeed.length() - 3; x > 0; x--) {
            for (int y = 0; y < POSSIBLE_CHARS.length(); y++) {
                String tempSeed = originalSeed.substring(0, x) + POSSIBLE_CHARS.substring(y, y + 1) + originalSeed.substring(x + 1);
                innerAddSeedsWithListOfSimilarSeeds1Char(similarSeeds, tempSeed);
            }
        }
        return similarSeeds;
    }

    public void findTypoSeed(String originalSeed, String addressToLookFor, int indexOffset) throws ArgumentException, InterruptedException, InvalidAddressException, InvalidSecurityLevelException {

        this.originalSeed = sanitizeSeed(originalSeed);

        System.out.println("Setting up iota API");
        IotaAPI api = new IotaAPI.Builder().build();

        System.out.println("Generating all possible combinations");
        List<String> similarSeeds = new ArrayList<String>();
        similarSeeds.add(this.originalSeed);
        addSeedsWithCopyPastError(similarSeeds);
        addSeedsWithRemovedCharacter1(similarSeeds);
        addSeedsWithDoubleCharacter1(similarSeeds);
        addSeedsWithReplacingChars(similarSeeds);
        addSeedsWithDoubleCharacter2(similarSeeds);
        addSeedsWithListOfDyslexiaSeeds(similarSeeds);
        addSeedsWithSimilarSeeds1Char(similarSeeds);
        addSeedsWithMissingRandomCharacter(similarSeeds);
        //addSeedsWithListOfSimilarSeeds2Chars(similarSeeds);


        System.out.println("Starting search (Checking " + similarSeeds.size() + " seeds)");
        for (int x = indexOffset; x < similarSeeds.size(); x++) {
            System.out.println("checking seed with index " + x + " (" + similarSeeds.get(x) + ")");
            GetNewAddressResponse addressResponse = api.getNewAddress(similarSeeds.get(x), 2, 0, true, 30, true);
            List<String> addresses = new ArrayList<String>();
            addresses.addAll(addressResponse.getAddresses().stream()
                    .map(address -> {
                        addresses.add(address.substring(0, 81));
                        return address;
                    })
                    .collect(Collectors.toList()));
            if (addresses.contains(addressToLookFor)) {
                System.out.println("");
                System.out.println("*****************************************************************************************************************");
                System.out.println("*                                                                                                               *");
                System.out.println("*  WALLET SEED FOUND!!!!!: " + similarSeeds.get(x) + "    *");
                System.out.println("*                                                                                                               *");
                System.out.println("*****************************************************************************************************************");
                System.out.println("*                                                                                                               *");
                System.out.println("*  If you have recovered your funds using this tool, please think of my children and make a donation ;-)        *");
                System.out.println("*  My iota address: " + DONATION_WALLET_ADDRESS + "  *");
                System.out.println("*                                                                                                               *");
                System.out.println("*****************************************************************************************************************");
                return;
            }
            Thread.sleep(10l);
        }
    }
}