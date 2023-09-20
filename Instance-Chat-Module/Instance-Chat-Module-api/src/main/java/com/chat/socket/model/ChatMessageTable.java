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
 * The table class for the &quot;DLF_ChatMessage&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see ChatMessage
 * @generated
 */
public class ChatMessageTable extends BaseTable<ChatMessageTable> {

	public static final ChatMessageTable INSTANCE = new ChatMessageTable();

	public final Column<ChatMessageTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ChatMessageTable, Long> id = createColumn(
		"id_", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<ChatMessageTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ChatMessageTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ChatMessageTable, Long> fromUser = createColumn(
		"fromUser", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ChatMessageTable, Long> toUser = createColumn(
		"toUser", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ChatMessageTable, Date> sentDate = createColumn(
		"sentDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ChatMessageTable, Date> readDate = createColumn(
		"readDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ChatMessageTable, String> message = createColumn(
		"message", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ChatMessageTable, Boolean> readed = createColumn(
		"readed", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<ChatMessageTable, Boolean> deleted = createColumn(
		"deleted", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<ChatMessageTable, String> fileName = createColumn(
		"fileName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ChatMessageTable, Long> fileId = createColumn(
		"fileId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);

	private ChatMessageTable() {
		super("DLF_ChatMessage", ChatMessageTable::new);
	}

}