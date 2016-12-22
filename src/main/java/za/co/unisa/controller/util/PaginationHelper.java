/*
 * Copyright (c) 2016, University of South Africa and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package za.co.unisa.controller.util;

import javax.faces.model.DataModel;

/**
 * 
 * @author Mufaro Benedict
 * @author mbaka motale
 */
public abstract class PaginationHelper {

    private int pageSize;
    private int page;

    /**
     *
     * @param pageSize
     */
    public PaginationHelper(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     *
     * @return
     */
    public abstract int getItemsCount();

    /**
     *
     * @return
     */
    public abstract DataModel createPageDataModel();

    /**
     *
     * @return
     */
    public int getPageFirstItem() {
        return page * pageSize;
    }

    /**
     *
     * @return
     */
    public int getPageLastItem() {
        int i = getPageFirstItem() + pageSize - 1;
        int count = getItemsCount() - 1;
        if (i > count) {
            i = count;
        }
        if (i < 0) {
            i = 0;
        }
        return i;
    }

    /**
     *
     * @return
     */
    public boolean isHasNextPage() {
        return (page + 1) * pageSize + 1 <= getItemsCount();
    }

    /**
     *
     */
    public void nextPage() {
        if (isHasNextPage()) {
            page++;
        }
    }

    /**
     *
     * @return
     */
    public boolean isHasPreviousPage() {
        return page > 0;
    }

    /**
     *
     */
    public void previousPage() {
        if (isHasPreviousPage()) {
            page--;
        }
    }

    /**
     *
     * @return
     */
    public int getPageSize() {
        return pageSize;
    }

}
