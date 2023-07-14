public class Part1 {
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
    
    public void testFindGene () {
        String dna = "TGACGAATTTGATAA";  // no ATG;
        System.out.println("DNA strand is " + dna);
        String gene = findGene(dna, 0);
        System.out.println("Gene is " + gene);
        
        dna = "TGATGTCAATAATTTGA";  // ATG + one valid stop codon;
        System.out.println("DNA strand is " + dna);
        gene = findGene(dna, 0);
        System.out.println("Gene is " + gene);
        
        dna = "CGATGATGTCAAGCTAATTTTGA";  // ATG + multiple valid stop codon;
        System.out.println("DNA strand is " + dna);
        gene = findGene(dna, 0);
        System.out.println("Gene is " + gene);
        
        dna = "ATGCGATCATGTCAAGCTAATTTTGA";  // ATG + no valid stop codon;
        System.out.println("DNA strand is " + dna);
        gene = findGene(dna, 0);
        System.out.println("Gene is " + gene);
        
        dna = "ATGCGATGATGTCAAGCTAATTTTGA";  // ATG + multiple valid stop codon;
        System.out.println("DNA strand is " + dna);
        gene = findGene(dna, 0);
        System.out.println("Gene is " + gene);
        
        dna = "AATGCTAACTAGCTGACTAAT";  // ATG + multiple valid stop codon;
        System.out.println("DNA strand is " + dna);
        gene = findGene(dna, 0);
        System.out.println("Gene is " + gene);
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
    
    public void testOn (String dna) {
        System.out.println("Testing printAllGenes on " + dna);
        printAllGenes(dna);
    }
    
    public void test () {
        testOn("TGACGAATTTGATAA");   // no ATG;
        testOn("");
        testOn("TGATGTCAATAATTTGA");  // ATG + one valid stop codon;
        testOn("CGATGATGTCAAGCTAATTTTGA");  // ATG + multiple valid stop codon;
        testOn("ATGCGATCATGTCAAGCTAATTTTGA");  // ATG + no valid stop codon;
        testOn("ATGCGATGATGTCAAGCTAATTTTGA");  // ATG + multiple valid stop codon;
        testOn("ATGCGATGATATGAAGCTAATTTGA");  // 2 x ATG + multiple valid stop codon;
    }
    
    public void testFindStopCodon () {
        String dna = "ATGTCACAGTGA";  // TGA index 9
        System.out.println("DNA strand is " + dna);
        int index = findStopCodon(dna, 0, "TGA");
        System.out.println("Index of stop codon is " + index);
        
        dna = "ATGTCACATGAATAG";  // TAG index 12 present TGA
        System.out.println("DNA strand is " + dna);
        index = findStopCodon(dna, 0, "TAG");
        System.out.println("Index of stop codon is " + index);
        
        dna = "ATGTAACAGTAA";  // TAA index 3 present TAA
        System.out.println("DNA strand is " + dna);
        index = findStopCodon(dna, 0, "TAA");
        System.out.println("Index of stop codon is " + index);
        
        dna = "ATGTCACAGTCA";  // nothing
        System.out.println("DNA strand is " + dna);
        index = findStopCodon(dna, 0, "TGA");
        System.out.println("Index of stop codon is " + index);
        
        dna = "ATGTCATGATAATAG";  // TGA index 6
        System.out.println("DNA strand is " + dna);
        index = findStopCodon(dna, 0, "TGA");
        System.out.println("Index of stop codon is " + index);
        
        dna = "ATGTCATTGATAAATGTAG";  // TGA index 6
        System.out.println("DNA strand is " + dna);
        index = findStopCodon(dna, 7, "TAG");
        System.out.println("Index of stop codon is " + index);
    }
    
}
