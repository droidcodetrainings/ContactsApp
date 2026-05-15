package com.droidcode.apps.contactsapp.server

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.droidcode.apps.contactsapp.conatacts.data.Contact
import com.droidcode.apps.contactsapp.conatacts.data.ContactsMock
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.engine.EmbeddedServer
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.netty.NettyApplicationEngine
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

class KtorServer : Service() {

    private val httpPort = 7070

    private var contacts: List<Contact> = emptyList()

    override fun onBind(p0: Intent?): IBinder?  = null

    override fun onCreate() {
        Log.i("KtorServer", "Starting Ktor server")
        contacts = ContactsMock().generateContactItems()
        getServer().start(wait = false)
        super.onCreate()
    }

    private fun getServer(): EmbeddedServer<NettyApplicationEngine, NettyApplicationEngine.Configuration> {
        return embeddedServer(Netty, port = httpPort) {
            install(ContentNegotiation) {
                json()
            }
            routing {
                get("/contacts") {
                    call.respond(contacts)
                }
            }
        }
    }
}
