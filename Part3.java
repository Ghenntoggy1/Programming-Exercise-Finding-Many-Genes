public class Part3 {
    public int findStopCodon (String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            int diff = currIndex - startIndex;
            if (diff % 3 == 0) {
                return currIndex;
            }
            else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length();
    }
    
    public String findGene (String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = Math.min(Math.min(taaIndex, tagIndex), tgaIndex);
        
        if (minIndex == dna.length()) {
            return "";
        }
        else {
            return dna.substring(startIndex, minIndex + 3);
        }
    }
    
    public void printAllGenes (String dna) {
        int startIndex = 0;
        while (true) {
            String currGene = findGene(dna, startIndex);
            if (currGene.isEmpty()) {
                break;
            }
            System.out.println(currGene);
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
    }
    
    public int countGenes (String dna) {
        int counter = 0;
        int startIndex = 0;
        while (true) {
            String currGene = findGene(dna, startIndex);
            if (currGene.isEmpty()) {
                break;
            }
            counter += 1;
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
        return counter;
    }
    
    public void testCountGenes () {
        String dna = "ATGTAAGATGCCCTAGT";
        System.out.println("DNA strand is " + dna);
        int counter = countGenes(dna);
        System.out.println("Number of genes is " + counter);
        printAllGenes(dna);
        
        dna = "";
        System.out.println("DNA strand is " + dna);
        counter = countGenes(dna);
        System.out.println("Number of genes is " + counter);
        printAllGenes(dna);
        
        dna = "ATGTAAATGTGAATGATGATGGTATCATAA";
        System.out.println("DNA strand is " + dna);
        counter = countGenes(dna);
        System.out.println("Number of genes is " + counter);
        printAllGenes(dna);
        
        dna = "ATGTAA";
        System.out.println("DNA strand is " + dna);
        counter = countGenes(dna);
        System.out.println("Number of genes is " + counter);
        printAllGenes(dna);
    }
}
