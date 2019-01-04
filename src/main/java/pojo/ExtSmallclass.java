package pojo;

/**
 * @ClassName pojo.ExtSmallclass
 * @Author tty
 * @Date 2019\1\2 0002 16:04
 * @Version 1.0
 */
public class ExtSmallclass extends Smallclass {
    private Bigclass bigclass;


    public ExtSmallclass(){

    }


    public ExtSmallclass(Bigclass bigclass) {
        this.bigclass = bigclass;
    }

    public ExtSmallclass(long id, String smallName, long smallBigId, String smallText, Bigclass bigclass) {
        super(id, smallName, smallBigId, smallText);
        this.bigclass = bigclass;
    }

    public Bigclass getBigclass() {
        return bigclass;
    }

    public void setBigclass(Bigclass bigclass) {
        this.bigclass = bigclass;
    }
}
