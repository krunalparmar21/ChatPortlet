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
 * The table class for the &quot;DLF_ChatConfigure&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see ChatConfigure
 * @generated
 */
public class ChatConfigureTable extends BaseTable<ChatConfigureTable> {

	public static final ChatConfigureTable INSTANCE = new ChatConfigureTable();

	public final Column<ChatConfigureTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ChatConfigureTable, Long> id = createColumn(
		"id_", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<ChatConfigureTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ChatConfigureTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ChatConfigureTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ChatConfigureTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ChatConfigureTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ChatConfigureTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ChatConfigureTable, Boolean> enable = createColumn(
		"enable", Boolean.class, Types.BOOLEAN, Column.FLAG_DEFAULT);
	public final Column<ChatConfigureTable, String> headerColor = createColumn(
		"headerColor", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ChatConfigureTable, String> chatRightColor =
		createColumn(
			"chatRightColor", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ChatConfigureTable, String> chatLeftColor =
		createColumn(
			"chatLeftColor", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ChatConfigureTable, String> headerTextColor =
		createColumn(
			"headerTextColor", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ChatConfigureTable, String> contentTextColor =
		createColumn(
			"contentTextColor", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<ChatConfigureTable, String> backColor = createColumn(
		"backColor", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ChatConfigureTable, String> chatRoleId = createColumn(
		"chatRoleId", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ChatConfigureTable, String> fontFamily = createColumn(
		"fontFamily", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ChatConfigureTable, Boolean> requestFeature =
		createColumn(
			"requestFeature", Boolean.class, Types.BOOLEAN,
			Column.FLAG_DEFAULT);

	private ChatConfigureTable() {
		super("DLF_ChatConfigure", ChatConfigureTable::new);
	}

}