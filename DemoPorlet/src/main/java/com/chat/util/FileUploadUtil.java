package com.chat.util;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.ResourceRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class FileUploadUtil {

    private static String ROOT_FOLDER_NAME = "Chat Images";
    private static String ROOT_FOLDER_DESCRIPTION = "This is chat images folder.";
    private static long PARENT_FOLDER_ID = 0L;


    private static Log _log = LogFactoryUtil.getLog(FileUploadUtil.class);

    public FileUploadUtil() {
    }

    public static DLFolder createFolder(ResourceRequest resourceRequest, ThemeDisplay themeDisplay) {
        DLFolder folder = null;
        long repositoryId = themeDisplay.getCompanyGroupId();

        try {
            folder = DLFolderLocalServiceUtil.getFolder(themeDisplay.getCompanyGroupId(), PARENT_FOLDER_ID, ROOT_FOLDER_NAME);
        } catch (Exception var8) {
            try {
                ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFolder.class.getName(), resourceRequest);
                folder = DLFolderLocalServiceUtil.addFolder(
                        null,
                        themeDisplay.getUserId(),
                        themeDisplay.getCompanyGroupId(),
                        repositoryId,
                        false,
                        PARENT_FOLDER_ID,
                        ROOT_FOLDER_NAME,
                        ROOT_FOLDER_DESCRIPTION,
                        true,
                        serviceContext);
            } catch (Exception var7) {
                System.out.println(var7.getStackTrace());
                _log.error(var7.getMessage());
            }
        }

        return folder;
    }

    public static Map<String, Object> fileUpload(ThemeDisplay themeDisplay, ResourceRequest resourceRequest) {
        UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(resourceRequest);
        Map<String, Object> fileMap = new HashMap();
        String fileName = uploadPortletRequest.getFileName("attachment");
        File file = uploadPortletRequest.getFile("attachment");
        String mimeType = uploadPortletRequest.getContentType("attachment");
        StringBuilder title = new StringBuilder();
        if (Validator.isNotNull(fileName)) {
            title.append(fileName.split("\\.")[0]);
            title.append(getAlphaNumericString(4));
        }

        String description = "";
        long repositoryId = themeDisplay.getCompanyGroupId();
        FileEntry fileEntry = null;
        //file.length
        try {
            if (Validator.isNotNull(file)) {
                DLFolder folder = createFolder(resourceRequest, themeDisplay);
                ServiceContext serviceContext = ServiceContextFactory.getInstance(DLFileEntry.class.getName(), resourceRequest);
                InputStream is = new FileInputStream(file);
                fileEntry = DLAppServiceUtil.addFileEntry( repositoryId, folder.getFolderId(), fileName, mimeType,
                        title.toString() ,description, "", file, serviceContext);
                fileMap.put("fileName", fileName);
                fileMap.put("fileId", fileEntry.getFileEntryId());
            }
        } catch (Exception var15) {
            _log.error(var15.getMessage());
        }

        return fileMap;
    }

    static String getAlphaNumericString(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);

        for(int i = 0; i < n; ++i) {
            int index = (int)((double)AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }
}
