package anmao.idoll.cater.San;

import anmao.idoll.cater.Cater;
import net.minecraft.world.item.ItemStack;

public class SanTags {
    private int Type = 0;
    public static final int TYPE_SUB = 2;
    public static final int TYPE_ADD = 1;

    public static final int _ADD_ONE = 1;
    public static final int _ADD_TWO = 3;
    public static final int _ADD_THREE = 5;
    public static final int _SUB_ONE = 1;
    public static final int _SUB_TWO = 3;
    public static final int _SUB_THREE = 5;
    private int Numbers = 0;

    public SanTags(){}
    public SanTags(int type, int numbers){
        this.Type = type;
        this.Numbers = numbers;
    }
    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public int getNumbers() {
        return Numbers;
    }

    public void setNumbers(int numbers) {
        Numbers = numbers;
    }
    public boolean isSanTags(ItemStack stack)
    {
        if (stack.is(Cater.SanAddOne)){
            this.setType(TYPE_ADD);
            this.setNumbers(_ADD_ONE);
            return true;
        }
        if (stack.is(Cater.SanAddTwo)){
            this.setType(TYPE_ADD);
            this.setNumbers(_ADD_TWO);
            return true;
        }
        if (stack.is(Cater.SanAddThree)){
            this.setType(TYPE_ADD);
            this.setNumbers(_ADD_THREE);
            return true;
        }
        if (stack.is(Cater.SanSubOne)){
            this.setType(TYPE_SUB);
            this.setNumbers(_SUB_ONE);
            return true;
        }
        if (stack.is(Cater.SanSubTwo)){
            this.setType(TYPE_SUB);
            this.setNumbers(_SUB_TWO);
            return true;
        }
        //if (stack.is(SanSubThree)){
        //    sanTagsType.setType(SanTagsType.TYPE_SUB);
        //    sanTagsType.setNumbers(SanTagsType._SUB_THREE);
        //    return true;
        //}
        return false;
    }
}
