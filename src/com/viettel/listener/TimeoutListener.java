/**
 * Copyright 2011 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.listener;

import com.viettel.utils.Timeout;

/**
 *  Timeout
 *  @author: HieuNH
 *  @version: 1.1
 *  @since: Oct 29, 2011
 */
public interface TimeoutListener{
	public void onTimeout (Timeout timeout);
}
