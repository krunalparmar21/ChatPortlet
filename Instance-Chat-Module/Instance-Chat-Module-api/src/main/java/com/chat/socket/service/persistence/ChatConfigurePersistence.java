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

package com.chat.socket.service.persistence;

import com.chat.socket.exception.NoSuchChatConfigureException;
import com.chat.socket.model.ChatConfigure;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the chat configure service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ChatConfigureUtil
 * @generated
 */
@ProviderType
public interface ChatConfigurePersistence
	extends BasePersistence<ChatConfigure> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ChatConfigureUtil} to access the chat configure persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the chat configures where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching chat configures
	 */
	public java.util.List<ChatConfigure> findByUuid(String uuid);

	/**
	 * Returns a range of all the chat configures where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ChatConfigureModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of chat configures
	 * @param end the upper bound of the range of chat configures (not inclusive)
	 * @return the range of matching chat configures
	 */
	public java.util.List<ChatConfigure> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the chat configures where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ChatConfigureModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of chat configures
	 * @param end the upper bound of the range of chat configures (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching chat configures
	 */
	public java.util.List<ChatConfigure> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ChatConfigure>
			orderByComparator);

	/**
	 * Returns an ordered range of all the chat configures where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ChatConfigureModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of chat configures
	 * @param end the upper bound of the range of chat configures (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching chat configures
	 */
	public java.util.List<ChatConfigure> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ChatConfigure>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first chat configure in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching chat configure
	 * @throws NoSuchChatConfigureException if a matching chat configure could not be found
	 */
	public ChatConfigure findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<ChatConfigure>
				orderByComparator)
		throws NoSuchChatConfigureException;

	/**
	 * Returns the first chat configure in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching chat configure, or <code>null</code> if a matching chat configure could not be found
	 */
	public ChatConfigure fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ChatConfigure>
			orderByComparator);

	/**
	 * Returns the last chat configure in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching chat configure
	 * @throws NoSuchChatConfigureException if a matching chat configure could not be found
	 */
	public ChatConfigure findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<ChatConfigure>
				orderByComparator)
		throws NoSuchChatConfigureException;

	/**
	 * Returns the last chat configure in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching chat configure, or <code>null</code> if a matching chat configure could not be found
	 */
	public ChatConfigure fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ChatConfigure>
			orderByComparator);

	/**
	 * Returns the chat configures before and after the current chat configure in the ordered set where uuid = &#63;.
	 *
	 * @param id the primary key of the current chat configure
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next chat configure
	 * @throws NoSuchChatConfigureException if a chat configure with the primary key could not be found
	 */
	public ChatConfigure[] findByUuid_PrevAndNext(
			long id, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<ChatConfigure>
				orderByComparator)
		throws NoSuchChatConfigureException;

	/**
	 * Removes all the chat configures where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of chat configures where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching chat configures
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the chat configure where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchChatConfigureException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching chat configure
	 * @throws NoSuchChatConfigureException if a matching chat configure could not be found
	 */
	public ChatConfigure findByUUID_G(String uuid, long groupId)
		throws NoSuchChatConfigureException;

	/**
	 * Returns the chat configure where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching chat configure, or <code>null</code> if a matching chat configure could not be found
	 */
	public ChatConfigure fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the chat configure where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching chat configure, or <code>null</code> if a matching chat configure could not be found
	 */
	public ChatConfigure fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the chat configure where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the chat configure that was removed
	 */
	public ChatConfigure removeByUUID_G(String uuid, long groupId)
		throws NoSuchChatConfigureException;

	/**
	 * Returns the number of chat configures where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching chat configures
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the chat configures where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching chat configures
	 */
	public java.util.List<ChatConfigure> findByUuid_C(
		String uuid, long companyId);

	/**
	 * Returns a range of all the chat configures where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ChatConfigureModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of chat configures
	 * @param end the upper bound of the range of chat configures (not inclusive)
	 * @return the range of matching chat configures
	 */
	public java.util.List<ChatConfigure> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the chat configures where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ChatConfigureModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of chat configures
	 * @param end the upper bound of the range of chat configures (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching chat configures
	 */
	public java.util.List<ChatConfigure> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ChatConfigure>
			orderByComparator);

	/**
	 * Returns an ordered range of all the chat configures where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ChatConfigureModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of chat configures
	 * @param end the upper bound of the range of chat configures (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching chat configures
	 */
	public java.util.List<ChatConfigure> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ChatConfigure>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first chat configure in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching chat configure
	 * @throws NoSuchChatConfigureException if a matching chat configure could not be found
	 */
	public ChatConfigure findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<ChatConfigure>
				orderByComparator)
		throws NoSuchChatConfigureException;

	/**
	 * Returns the first chat configure in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching chat configure, or <code>null</code> if a matching chat configure could not be found
	 */
	public ChatConfigure fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ChatConfigure>
			orderByComparator);

	/**
	 * Returns the last chat configure in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching chat configure
	 * @throws NoSuchChatConfigureException if a matching chat configure could not be found
	 */
	public ChatConfigure findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<ChatConfigure>
				orderByComparator)
		throws NoSuchChatConfigureException;

	/**
	 * Returns the last chat configure in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching chat configure, or <code>null</code> if a matching chat configure could not be found
	 */
	public ChatConfigure fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ChatConfigure>
			orderByComparator);

	/**
	 * Returns the chat configures before and after the current chat configure in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param id the primary key of the current chat configure
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next chat configure
	 * @throws NoSuchChatConfigureException if a chat configure with the primary key could not be found
	 */
	public ChatConfigure[] findByUuid_C_PrevAndNext(
			long id, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<ChatConfigure>
				orderByComparator)
		throws NoSuchChatConfigureException;

	/**
	 * Removes all the chat configures where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of chat configures where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching chat configures
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the chat configures where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching chat configures
	 */
	public java.util.List<ChatConfigure> findByGroupId(long groupId);

	/**
	 * Returns a range of all the chat configures where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ChatConfigureModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of chat configures
	 * @param end the upper bound of the range of chat configures (not inclusive)
	 * @return the range of matching chat configures
	 */
	public java.util.List<ChatConfigure> findByGroupId(
		long groupId, int start, int end);

	/**
	 * Returns an ordered range of all the chat configures where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ChatConfigureModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of chat configures
	 * @param end the upper bound of the range of chat configures (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching chat configures
	 */
	public java.util.List<ChatConfigure> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ChatConfigure>
			orderByComparator);

	/**
	 * Returns an ordered range of all the chat configures where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ChatConfigureModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of chat configures
	 * @param end the upper bound of the range of chat configures (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching chat configures
	 */
	public java.util.List<ChatConfigure> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ChatConfigure>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first chat configure in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching chat configure
	 * @throws NoSuchChatConfigureException if a matching chat configure could not be found
	 */
	public ChatConfigure findByGroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<ChatConfigure>
				orderByComparator)
		throws NoSuchChatConfigureException;

	/**
	 * Returns the first chat configure in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching chat configure, or <code>null</code> if a matching chat configure could not be found
	 */
	public ChatConfigure fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ChatConfigure>
			orderByComparator);

	/**
	 * Returns the last chat configure in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching chat configure
	 * @throws NoSuchChatConfigureException if a matching chat configure could not be found
	 */
	public ChatConfigure findByGroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<ChatConfigure>
				orderByComparator)
		throws NoSuchChatConfigureException;

	/**
	 * Returns the last chat configure in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching chat configure, or <code>null</code> if a matching chat configure could not be found
	 */
	public ChatConfigure fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ChatConfigure>
			orderByComparator);

	/**
	 * Returns the chat configures before and after the current chat configure in the ordered set where groupId = &#63;.
	 *
	 * @param id the primary key of the current chat configure
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next chat configure
	 * @throws NoSuchChatConfigureException if a chat configure with the primary key could not be found
	 */
	public ChatConfigure[] findByGroupId_PrevAndNext(
			long id, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<ChatConfigure>
				orderByComparator)
		throws NoSuchChatConfigureException;

	/**
	 * Removes all the chat configures where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeByGroupId(long groupId);

	/**
	 * Returns the number of chat configures where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching chat configures
	 */
	public int countByGroupId(long groupId);

	/**
	 * Caches the chat configure in the entity cache if it is enabled.
	 *
	 * @param chatConfigure the chat configure
	 */
	public void cacheResult(ChatConfigure chatConfigure);

	/**
	 * Caches the chat configures in the entity cache if it is enabled.
	 *
	 * @param chatConfigures the chat configures
	 */
	public void cacheResult(java.util.List<ChatConfigure> chatConfigures);

	/**
	 * Creates a new chat configure with the primary key. Does not add the chat configure to the database.
	 *
	 * @param id the primary key for the new chat configure
	 * @return the new chat configure
	 */
	public ChatConfigure create(long id);

	/**
	 * Removes the chat configure with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the chat configure
	 * @return the chat configure that was removed
	 * @throws NoSuchChatConfigureException if a chat configure with the primary key could not be found
	 */
	public ChatConfigure remove(long id) throws NoSuchChatConfigureException;

	public ChatConfigure updateImpl(ChatConfigure chatConfigure);

	/**
	 * Returns the chat configure with the primary key or throws a <code>NoSuchChatConfigureException</code> if it could not be found.
	 *
	 * @param id the primary key of the chat configure
	 * @return the chat configure
	 * @throws NoSuchChatConfigureException if a chat configure with the primary key could not be found
	 */
	public ChatConfigure findByPrimaryKey(long id)
		throws NoSuchChatConfigureException;

	/**
	 * Returns the chat configure with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the chat configure
	 * @return the chat configure, or <code>null</code> if a chat configure with the primary key could not be found
	 */
	public ChatConfigure fetchByPrimaryKey(long id);

	/**
	 * Returns all the chat configures.
	 *
	 * @return the chat configures
	 */
	public java.util.List<ChatConfigure> findAll();

	/**
	 * Returns a range of all the chat configures.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ChatConfigureModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of chat configures
	 * @param end the upper bound of the range of chat configures (not inclusive)
	 * @return the range of chat configures
	 */
	public java.util.List<ChatConfigure> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the chat configures.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ChatConfigureModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of chat configures
	 * @param end the upper bound of the range of chat configures (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of chat configures
	 */
	public java.util.List<ChatConfigure> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ChatConfigure>
			orderByComparator);

	/**
	 * Returns an ordered range of all the chat configures.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ChatConfigureModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of chat configures
	 * @param end the upper bound of the range of chat configures (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of chat configures
	 */
	public java.util.List<ChatConfigure> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ChatConfigure>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the chat configures from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of chat configures.
	 *
	 * @return the number of chat configures
	 */
	public int countAll();

}