package com.datarangers.sdk.client;

import com.datarangers.sdk.RangersClient;
import com.sun.management.UnixOperatingSystemMXBean;
import org.junit.Assume;
import org.junit.Test;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ClientResourceTest {
    private static final int UPLOAD_ATTEMPTS = 32;

    @Test
    public void uploadFileClosesInputStreamWhenRequestPreparationFails() throws Exception {
        java.lang.management.OperatingSystemMXBean operatingSystemMXBean =
                ManagementFactory.getOperatingSystemMXBean();
        Assume.assumeTrue(operatingSystemMXBean instanceof UnixOperatingSystemMXBean);
        UnixOperatingSystemMXBean unixOperatingSystemMXBean =
                (UnixOperatingSystemMXBean) operatingSystemMXBean;

        File file = File.createTempFile("datafinder-upload", ".txt");
        try {
            Files.write(file.toPath(), "content".getBytes(StandardCharsets.UTF_8));
            RangersClient client = new RangersClient("ak", "sk", "http://localhost");
            long descriptorsBefore = unixOperatingSystemMXBean.getOpenFileDescriptorCount();

            for (int i = 0; i < UPLOAD_ATTEMPTS; i++) {
                try {
                    client.uploadFile("INVALID", "/unused", null, null, file);
                    fail("unsupported method should fail during request preparation");
                } catch (Exception expected) {
                    // The file must already be closed when request preparation fails.
                }
            }

            long descriptorsAfter = unixOperatingSystemMXBean.getOpenFileDescriptorCount();
            assertTrue(
                    "uploadFile leaked file descriptors: before=" + descriptorsBefore
                            + ", after=" + descriptorsAfter,
                    descriptorsAfter - descriptorsBefore <= 2);
        } finally {
            Files.deleteIfExists(file.toPath());
        }
    }
}
