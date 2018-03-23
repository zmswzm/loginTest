package core.support.filter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class InterceptorResponseWrapper extends HttpServletResponseWrapper {

    /**
     * 输出管道
     */
    public enum OutputChannel {

        /**
         * 调用 getWriter()
         */
        WRITER,

        /**
         * 调用 getOutputStream()
         */
        STREAM,

        /**
         * 从未调用
         */
        NEVER
    }

    /**
     * 自定义 PrintWriter
     */
    private static class InterceptorInternalRespPrintWriter extends PrintWriter {

        private ByteArrayOutputStream opStream;

        public InterceptorInternalRespPrintWriter(ByteArrayOutputStream output) {

            super(output);
            opStream = output;
        }

        public ByteArrayOutputStream getByteArrayOutputStream() {
            return opStream;
        }
    }

    private ByteArrayOutputStream opStream;

    private InterceptorInternalRespPrintWriter writer;

    private OutputChannel outputChannel;

    public InterceptorResponseWrapper(HttpServletResponse httpServletResponse) {

        super(httpServletResponse);
        opStream = new ByteArrayOutputStream();
        writer = new InterceptorInternalRespPrintWriter(opStream);
    }

    @Override
    public PrintWriter getWriter() throws IOException {

        outputChannel = OutputChannel.WRITER;

        return writer;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {

        outputChannel = OutputChannel.STREAM;

        return super.getOutputStream();
    }

    @Override
    public void finalize() throws Throwable {

        super.finalize();
        opStream.close();
        writer.close();
    }

    /**
     * 获取 response 内容
     *
     * @return
     */
    public String getContent() {

        writer.flush();

        return writer.getByteArrayOutputStream().toString().trim();
    }

    /**
     * 获取输出管道
     *
     * @return
     */
    public OutputChannel getOutputChannel(){

        return outputChannel != null ? outputChannel : OutputChannel.NEVER;
    }
}
