package com.chat.socket.action;


import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

@Component(
        immediate = true,
        property = {"javax.portlet.name=com_socket_portlet_SocketPortlet", "mvc.command.name=/downloadFile"},
        service = {MVCResourceCommand.class}
)
public class DownloadDocumentMVCResourceCommand extends BaseMVCResourceCommand {

    public DownloadDocumentMVCResourceCommand() {
    }

    protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
        Long fileId = ParamUtil.getLong(resourceRequest, "fileId");
        FileEntry fileEntry = DLAppServiceUtil.getFileEntry(fileId);
        String[] originalFileName = fileEntry.getFileName().split("\\.");
        StringBuilder fileName = new StringBuilder();
        fileName.append(originalFileName[0].subSequence(0, originalFileName[0].length() - 4));
        fileName.append(".");
        fileName.append(originalFileName[1]);

        try {
            PortletResponseUtil.sendFile(resourceRequest, resourceResponse, fileName.toString(), fileEntry.getContentStream(), 0, fileEntry.getMimeType(), "attachment");
        } catch (Exception var8) {
            var8.printStackTrace();
        }

    }
}
