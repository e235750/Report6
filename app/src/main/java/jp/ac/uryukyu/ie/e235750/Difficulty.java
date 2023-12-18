package jp.ac.uryukyu.ie.e235750;

/**
 * 難易度に応じて生成されるセルの個数をセットするメソッドをそれぞれのオブジェクトに実装
 */
public enum Difficulty {
    BEGINNER{
        @Override
        public void setDifficulty(Cell cell) {
            Cell.setConstant(4, 4, 4);            
        }
    },
    
    INTERMEDIATE{
        @Override
        public void setDifficulty(Cell cell) {
            Cell.setConstant(5, 5, 5);            
        }
    },

    ADVANCED{
        @Override
        public void setDifficulty(Cell cell) {
            Cell.setConstant(7, 7, 10);            
        }
    };

    /**
     * @param cell //setConstantメソッドを呼び出すために指定
     */
    public abstract void setDifficulty(Cell cell);

    /**
     * 
     * @param index //enumのindex, 0 -> BEGINNER, 1 -> INTERMEDIATE, 2 -> ADVANCED
     * @param cell //setConstantメソッドを呼び出すために指定
     */
    public static void executeSetDifficulty(int index, Cell cell){
        Difficulty[] diff = Difficulty.values();
        if(index >= 0 && index < diff.length){
            diff[index].setDifficulty(cell);
        }
    }
}
