package com.scireum.siriusstartupnotify

import com.intellij.execution.filters.ConsoleDependentFilterProvider
import com.intellij.execution.filters.Filter
import com.intellij.execution.ui.ConsoleView
import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.project.Project
import com.intellij.psi.search.GlobalSearchScope

class SiriusStartupNotifyFilterProvider : ConsoleDependentFilterProvider() {

    override fun getDefaultFilters(
        consoleView: ConsoleView,
        project: Project,
        scope: GlobalSearchScope
    ): Array<Filter> {
        return arrayOf(
            Filter { line, _ ->
                if (line.contains("System is UP and RUNNING")) {
                    showNotification(project)
                }
                null
            }
        )
    }

    private fun showNotification(project: Project) {
        val notification = Notification(
            "Sirius Startup",
            "System is UP and RUNNING",
            "The sirius framework has now fully started up and the application is ready to be used.",
            NotificationType.INFORMATION
        )
        Notifications.Bus.notify(notification, project)
    }
}
