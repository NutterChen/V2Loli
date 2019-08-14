package com.v2ray.loli.dto

data class V2rayConfig(val port: Int,
                       val log: LogBean,
                       val inbound: InboundBean,
                       var inboundDetour: List<InboundDetourBean>?,
                       var outbound: OutboundBean,
                       val outboundDetour: List<OutboundDetourBean>,
                       val dns: DnsBean,
                       val routing: RoutingBean) {

    data class LogBean(val access: String,
                       val error: String,
                       val loglevel: String)

    data class InboundBean(
            val listen: String,
            //val port: Int,
            val protocol: String,
            val settings: InSettingsBean,
            val sniffing: SniffingBean) {

        data class InSettingsBean(val auth: String,
                                  val udp: Boolean)

        data class SniffingBean(val enabled: Boolean,
                                val destOverride: List<String>)
    }

    data class OutboundBean(val tag: String,
                            var protocol: String,
                            var settings: OutSettingsBean,
                            var streamSettings: StreamSettingsBean,
                            var mux: MuxBean) {

        data class OutSettingsBean(var vnext: List<VnextBean>?,
                                   var servers: List<ServersBean>?) {

            data class VnextBean(var address: String,
                                 var port: Int,
                                 var users: List<UsersBean>) {

                data class UsersBean(var id: String,
                                     var alterId: Int,
                                     var security: String)
            }

            data class ServersBean(var address: String,
                                   var method: String,
                                   var ota: Boolean,
                                   var password: String,
                                   var port: Int,
                                   var level: Int)

        }

        data class StreamSettingsBean(var network: String,
                                      var security: String,
                                      var tcpSettings: TcpsettingsBean?,
                                      var kcpsettings: KcpsettingsBean?,
                                      var wssettings: WssettingsBean?,
                                      var httpsettings: HttpsettingsBean?,
                                      var tlssettings: TlssettingsBean?
        ) {

            data class TcpsettingsBean(var connectionReuse: Boolean = true,
                                       var header: HeaderBean = HeaderBean()) {
                data class HeaderBean(var type: String = "none",
                                      var request: Any? = null,
                                      var response: Any? = null)
            }

            data class KcpsettingsBean(var mtu: Int = 1460,
                                       var tti: Int = 10,
                                       var uplinkCapacity: Int = 12,
                                       var downlinkCapacity: Int = 100,
                                       var congestion: Boolean = true,
                                       var readBufferSize: Int = 2,
                                       var writeBufferSize: Int = 2,
                                       var header: HeaderBean = HeaderBean()) {
                data class HeaderBean(var type: String = "none")
            }

            data class WssettingsBean(var connectionReuse: Boolean = true,
                                      var path: String = "",
                                      var headers: HeadersBean = HeadersBean()) {
                data class HeadersBean(var Host: String = "")
            }

            data class HttpsettingsBean(var host: List<String> = ArrayList<String>(), var path: String = "")

            data class TlssettingsBean(var allowInsecure: Boolean = true,
                                       var serverName: String = "")
        }
    }

    data class MuxBean(var enabled: Boolean)

    data class InboundDetourBean(
            var port: Int,
            val listen: String,
            val protocol: String,
            val settings: InSettingsBean,
            val sniffing: SniffingBean) {

        data class InSettingsBean(val auth: String,
                                  val udp: Boolean)

        data class SniffingBean(val enabled: Boolean,
                                val destOverride: List<String>)
    }

    data class OutboundDetourBean(val protocol: String,
                                  var settings: OutboundDetourSettingsBean,
                                  val tag: String) {
        data class OutboundDetourSettingsBean(var response: Response) {
            data class Response(var type: String)
        }
    }

    data class DnsBean(var servers: List<String>)

    data class RoutingBean(val strategy: String,
                           val settings: SettingsBean) {

        data class SettingsBean(val domainStrategy: String,
                                var rules: ArrayList<RulesBean>) {

            data class RulesBean(var type: String,
                    //var port: String,
                                 var ip: ArrayList<String>?,
                                 var domain: ArrayList<String>?,
                                 var outboundTag: String)
        }
    }
}