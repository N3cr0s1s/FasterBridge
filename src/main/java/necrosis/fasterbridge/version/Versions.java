package necrosis.fasterbridge.version;

public enum Versions {
    V1_8_R1("1_8_R1",8),
    V1_8_R2("1_8_R1",8),
    V1_8_R3("1_8_R1",8),

    V1_9_R1("1_9_R1",9),
    V1_9_R2("1_9_R2",9),

    V1_10_R1("1_10_R1",10),

    V1_11_R1("1_11_R1",11),

    V1_12_R1("1_12_R1",12),

    V1_13_R1("1_13_R1",13),
    V1_13_R2("1_13_R2",13),

    V1_14_R1("1_14_R1",14),

    V1_15_R1("1_15_R1",15),

    V1_16_R1("1_16_R1",16),
    V1_16_R2("1_16_R2",16),
    V1_16_R3("1_16_R3",16),

    V1_17_R1("1_17_R1",17);

    private String name;
    private int version;

    Versions(String name,int version){
        this.name       =   name;
        this.version    =   version;
    }

    //  Get version name
    public String getName(){
        return this.name;
    }

    //  Get version simpler number
    public int getVersion(){
        return this.version;
    }
}
