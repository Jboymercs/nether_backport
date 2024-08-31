package com.unseen.nb.common.blocks.base;

public interface IMetaItem {
    default String byMeta(int meta) {
        return null;
    }

    default int getMaxMeta() {
        return 0;
    }
}
