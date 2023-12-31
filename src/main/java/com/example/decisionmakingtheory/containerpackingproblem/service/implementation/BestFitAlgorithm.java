package com.example.decisionmakingtheory.containerpackingproblem.service.implementation;

import com.example.decisionmakingtheory.containerpackingproblem.domain.OrderType;

import java.util.List;

public class BestFitAlgorithm extends FirstFitAlgorithm {

    public BestFitAlgorithm() {
        super(OrderType.UNORDERED);
    }

    public BestFitAlgorithm(OrderType o) {
        super(o.getOpposite());
    }

    @Override
    protected int findFit(List<Integer> containers, int good, int carryingCapacityOfContainer) {
        if (getOrder() != OrderType.UNORDERED){
            return super.findFit(containers, good, carryingCapacityOfContainer);
        }
        int previous = containers.size() - 1;
        int fullestIndex = -1;
        int fullestSize = -1;
        for (int i = 0; i < previous; i++) {
            int curSize = containers.get(i);
            if (curSize + good <= carryingCapacityOfContainer && curSize > fullestSize) {
                fullestIndex = i;
                fullestSize = curSize;
            }
        }
        return fullestIndex;
    }

    @Override
    protected int calculateComplexity(int size, int fit) {
        return switch (getOrder()) {
            case ASCENDING -> size - fit - 1;
            case UNORDERED -> size - 1;
            case DESCENDING -> fit + 1;
        };
    }

    @Override
    public String getShortName(){
        return "BFA";
    }
}
