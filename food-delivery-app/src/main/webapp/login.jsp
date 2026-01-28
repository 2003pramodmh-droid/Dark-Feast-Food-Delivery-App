<%@ include file="header.jsp" %>

    <div class="form-container">
        <div class="form-card">
            <h1 style="font-size: 2rem; margin-bottom: 1rem;">Traveler's Login</h1>

            <% String error=request.getParameter("error"); String msg=request.getParameter("msg"); %>

                <% if(error !=null) { %>
                    <div class="message error"><%= error %>
                    </div>
                    <% } %>

                        <% if(msg !=null) { %>
                            <div class="message success"><%= msg %>
                            </div>
                            <% } %>

                                <form action="auth" method="post">
                                    <input type="hidden" name="action" value="login">

                                    <label for="username">Username</label>
                                    <input type="text" id="username" name="username" placeholder="Enter your username"
                                        required>

                                    <label for="password">Password</label>
                                    <input type="password" id="password" name="password"
                                        placeholder="Enter your password" required>

                                    <button type="submit" class="btn btn-primary"
                                        style="width:100%; margin-top: 1.5rem;">
                                        Enter the Realm
                                    </button>
                                </form>

                                <p style="text-align:center; margin-top: 1.5rem; color: var(--text-secondary);">
                                    New adventurer?
                                    <a href="register.jsp"
                                        style="color: var(--accent-gold); text-decoration: none; font-weight: bold;">
                                        Join the Guild
                                    </a>
                                </p>

                                <p style="text-align:center; margin-top: 1rem;">
                                    <a href="home" style="color: var(--text-muted); text-decoration: none;">
                                        Continue as Guest
                                    </a>
                                </p>
        </div>
    </div>

    <%@ include file="footer.jsp" %>