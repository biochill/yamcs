package com.spaceapplications.yamcs.scpi.telnet;

import java.util.List;

import com.spaceapplications.yamcs.scpi.Config;
import com.spaceapplications.yamcs.scpi.Device;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class TelnetServerInitializer extends ChannelInitializer<SocketChannel> {

    // These are marked as '@Sharable'
    private static final StringDecoder STRING_DECODER = new StringDecoder(CharsetUtil.US_ASCII);
    private static final StringEncoder STRING_ENCODER = new StringEncoder(CharsetUtil.US_ASCII);

    private Config config;
    private List<Device> devices;

    public TelnetServerInitializer(Config config, List<Device> devices) {
        this.config = config;
        this.devices = devices;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("frameDecoder", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast("stringDecoder", STRING_DECODER);
        pipeline.addLast("stringEncoder", STRING_ENCODER);

        pipeline.addLast(new TelnetServerHandler(config, devices));
    }
}
