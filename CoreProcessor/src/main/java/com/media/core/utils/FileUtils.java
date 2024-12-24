package com.media.core.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.util.ResourceUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtils {
    public static ByteArrayOutputStream readFileServerFromPath(String path) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try (FileInputStream inputStream = new FileInputStream(ResourceUtils.getFile(path))){
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, length);
            }
            return byteArrayOutputStream;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
//    public static DataSource getDatasourceByByteArrayOutputStream(ByteArrayOutputStream byteArrayOutputStream) {
//        if(byteArrayOutputStream == null) {
//            throw new IllegalArgumentException("File không tồn tại");
//        }
//        return new ByteArrayDataSource(byteArrayOutputStream.toByteArray(),  "application/octet-stream");
//    }
    
    
    //#send email with attachment + nội dung chứa icon
//    public void sendEmailWithAttachment(MailStructure mailStructure, List<MailFileInfo> attachments, List<MailFileInfo> imageIcons) {
//        String requestId = UUID.randomUUID().toString();
//        String request = null;
//        String errMessage = null;
//        try {
//            request = objectMapper.writeValueAsString(mailStructure);
//
//            if(mailStructure() == null){
//                throw new IllegalArgumentException("Email không được để trống");
//            }
//
//            //validate
//            for(String email: mailStructure()){
//                if(StringUtils.stringIsNullOrEmpty(email)){
//                    throw new IllegalArgumentException("Email không được để trống");
//                }
//            }
//
//            final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//            mailSender.setHost(mailProperties.getHost());
//            mailSender.setPort(mailProperties.getPort());
//            mailSender.setDefaultEncoding(StandardCharsets.UTF_8.name());
//            mailSender.setUsername(mailProperties.getUsername());
//            mailSender.setPassword(mailProperties.getPassword());
//
//            final Properties props = mailSender.getJavaMailProperties();
//            props.put("mail.transport.protocol", mailProperties.getProtocol());
//            props.put("mail.smtp.auth", mailProperties.getAuth());
//            props.put("mail.smtp.starttls.enable", mailProperties.getStarttlsEnable());
//            //props.put("mail.debug", "true"); //bật khi cần debug
//            props.put("mail.smtp.ssl.trust", mailProperties.getTrustHost());
//
//            final MimeMessage mimeMessage = mailSender.createMimeMessage();
//            final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
//            mimeMessageHelper.setFrom("#");
//            mimeMessageHelper.setTo(mailStructureDTO.getToMail());
//            mimeMessageHelper.setText("<html><body>" + mailStructure() + "</body></html>", true);
//            mimeMessageHelper.setSubject(mailStructure.getSubject());
//            mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis()));
//            mimeMessageHelper.setValidateAddresses(true);
//
//            //add attachment into mail
//            if(!attachments.isEmpty()){
//                for(MailFileInfo attachment: attachments){
//                    mimeMessageHelper.addAttachment(attachment.getFileName(),  attachment.getFile());
//                }
//            }
//
//            // replace the file into contentId
//            if(!imageIcons.isEmpty()){
//                for(MailFileInfo imageIcon: imageIcons){
//                    mimeMessageHelper.addInline(imageIcon.getKey(), imageIcon.getFile());
//                    mimeMessageHelper.getMimeMultipart().getBodyPart("<" + imageIcon.getKey() + ">")
//                                                        .setFileName(imageIcon.getFileName());
//                }
//            }
//
//            // Sending the mail
//            mailSender.send(mimeMessage);
//            log.info("Log When Send Mail Success: " + LocalDateTime.now());
//        } catch (MailException | MessagingException e) {
//            e.printStackTrace();
//            errMessage = e.getMessage();
//        } catch (Exception e) {
//            e.printStackTrace();
//            errMessage = e.getMessage();
//        } finally {
//     
//        }
//    }
}
