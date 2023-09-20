/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.chat.socket.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;DLF_ChatRequest&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see ChatRequest
 * @generated
 */
public class ChatRequestTable extends BaseTable<ChatRequestTable> {

	public static final ChatRequestTable INSTANCE = new ChatRequestTable();

	public final Column<ChatRequestTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ChatRequestTable, Long> id = createColumn(
		"id_", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<ChatRequestTable, Long> fromUserId = createColumn(
		"fromUserId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ChatRequestTable, Long> toUserId = createColumn(
		"toUserId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ChatRequestTable, Boolean> isAccepted = createColumn(
		"isAccepted", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<ChatRequestTable, Boolean> isRejected = createColumn(
		"isRejected", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<ChatRequestTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ChatRequestTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private ChatRequestTable() {
		super("DLF_ChatRequest", ChatRequestTable::new);
	}

}