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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the ChatRequest service. Represents a row in the &quot;DLF_ChatRequest&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.chat.socket.model.impl.ChatRequestModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.chat.socket.model.impl.ChatRequestImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ChatRequest
 * @generated
 */
@ProviderType
public interface ChatRequestModel extends BaseModel<ChatRequest> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a chat request model instance should use the {@link ChatRequest} interface instead.
	 */

	/**
	 * Returns the primary key of this chat request.
	 *
	 * @return the primary key of this chat request
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this chat request.
	 *
	 * @param primaryKey the primary key of this chat request
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this chat request.
	 *
	 * @return the uuid of this chat request
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this chat request.
	 *
	 * @param uuid the uuid of this chat request
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the ID of this chat request.
	 *
	 * @return the ID of this chat request
	 */
	public long getId();

	/**
	 * Sets the ID of this chat request.
	 *
	 * @param id the ID of this chat request
	 */
	public void setId(long id);

	/**
	 * Returns the from user ID of this chat request.
	 *
	 * @return the from user ID of this chat request
	 */
	public long getFromUserId();

	/**
	 * Sets the from user ID of this chat request.
	 *
	 * @param fromUserId the from user ID of this chat request
	 */
	public void setFromUserId(long fromUserId);

	/**
	 * Returns the from user uuid of this chat request.
	 *
	 * @return the from user uuid of this chat request
	 */
	public String getFromUserUuid();

	/**
	 * Sets the from user uuid of this chat request.
	 *
	 * @param fromUserUuid the from user uuid of this chat request
	 */
	public void setFromUserUuid(String fromUserUuid);

	/**
	 * Returns the to user ID of this chat request.
	 *
	 * @return the to user ID of this chat request
	 */
	public long getToUserId();

	/**
	 * Sets the to user ID of this chat request.
	 *
	 * @param toUserId the to user ID of this chat request
	 */
	public void setToUserId(long toUserId);

	/**
	 * Returns the to user uuid of this chat request.
	 *
	 * @return the to user uuid of this chat request
	 */
	public String getToUserUuid();

	/**
	 * Sets the to user uuid of this chat request.
	 *
	 * @param toUserUuid the to user uuid of this chat request
	 */
	public void setToUserUuid(String toUserUuid);

	/**
	 * Returns the is accepted of this chat request.
	 *
	 * @return the is accepted of this chat request
	 */
	public boolean getIsAccepted();

	/**
	 * Returns <code>true</code> if this chat request is is accepted.
	 *
	 * @return <code>true</code> if this chat request is is accepted; <code>false</code> otherwise
	 */
	public boolean isIsAccepted();

	/**
	 * Sets whether this chat request is is accepted.
	 *
	 * @param isAccepted the is accepted of this chat request
	 */
	public void setIsAccepted(boolean isAccepted);

	/**
	 * Returns the is rejected of this chat request.
	 *
	 * @return the is rejected of this chat request
	 */
	public boolean getIsRejected();

	/**
	 * Returns <code>true</code> if this chat request is is rejected.
	 *
	 * @return <code>true</code> if this chat request is is rejected; <code>false</code> otherwise
	 */
	public boolean isIsRejected();

	/**
	 * Sets whether this chat request is is rejected.
	 *
	 * @param isRejected the is rejected of this chat request
	 */
	public void setIsRejected(boolean isRejected);

	/**
	 * Returns the create date of this chat request.
	 *
	 * @return the create date of this chat request
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this chat request.
	 *
	 * @param createDate the create date of this chat request
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this chat request.
	 *
	 * @return the modified date of this chat request
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this chat request.
	 *
	 * @param modifiedDate the modified date of this chat request
	 */
	public void setModifiedDate(Date modifiedDate);

	@Override
	public ChatRequest cloneWithOriginalValues();

	public default String toXmlString() {
		return null;
	}

}