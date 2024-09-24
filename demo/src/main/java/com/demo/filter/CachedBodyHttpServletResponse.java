package com.demo.filter;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class CachedBodyHttpServletResponse extends HttpServletResponseWrapper {

    private ByteArrayOutputStream cachedBody;

    public CachedBodyHttpServletResponse(HttpServletResponse response) {
        super(response);
        this.cachedBody = new ByteArrayOutputStream();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new CachedBodyServletOutputStream(this.cachedBody, super.getOutputStream());
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(getOutputStream());
    }

    public byte[] getCachedBody() {
        return this.cachedBody.toByteArray();
    }

    private static class CachedBodyServletOutputStream extends ServletOutputStream {

        private final ByteArrayOutputStream cachedBody;
        private final ServletOutputStream outputStream;

        public CachedBodyServletOutputStream(ByteArrayOutputStream cachedBody, ServletOutputStream outputStream) {
            this.cachedBody = cachedBody;
            this.outputStream = outputStream;
        }

        @Override
        public boolean isReady() {
            return outputStream.isReady();
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {
            outputStream.setWriteListener(writeListener);
        }

        @Override
        public void write(int b) throws IOException {
            cachedBody.write(b);
            outputStream.write(b);
        }
    }
}
