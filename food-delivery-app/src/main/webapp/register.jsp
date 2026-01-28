<%@ include file="header.jsp" %>

    <div class="form-container">
        <div class="form-card">
            <h1 style="font-size: 2rem; margin-bottom: 1rem;">Join the Guild</h1>

            <% String error=request.getParameter("error"); %>

                <% if(error !=null) { %>
                    <div class="message error"><%= error %>
                    </div>
                    <% } %>

                        <form action="auth" method="post">
                            <input type="hidden" name="action" value="register">

                            <label for="username">Username</label>
                            <input type="text" id="username" name="username" placeholder="Choose a unique username"
                                required>

                            <label for="password">Password</label>
                            <input type="password" id="password" name="password" placeholder="Create a strong password"
                                required>

                            <label for="email">Email (Optional)</label>
                            <input type="email" id="email" name="email" placeholder="your.email@example.com">

                            <label for="address">Address (Optional)</label>
                            <textarea id="address" name="address" rows="3"
                                placeholder="Your delivery address"></textarea>

                            <button type="submit" class="btn btn-primary" style="width:100%; margin-top: 1.5rem;">
                                Create Account
                            </button>
                        </form>

                        <p style="text-align:center; margin-top: 1.5rem; color: var(--text-secondary);">
                            Already a member?
                            <a href="login.jsp"
                                style="color: var(--accent-gold); text-decoration: none; font-weight: bold;">
                                Login Here
                            </a>
                        </p>
        </div>
    </div>

    <%@ include file="footer.jsp" %>