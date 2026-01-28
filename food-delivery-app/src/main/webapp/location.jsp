<%@ include file="header.jsp" %>

    <% String totalParam=request.getParameter("total"); if(totalParam==null ||
        session.getAttribute("loggedInUser")==null) { response.sendRedirect("cart"); return; } double
        total=Double.parseDouble(totalParam); String error=request.getParameter("error"); %>

        <div class="form-container">
            <div class="form-card">
                <h1 style="font-size: 2rem; margin-bottom: 1rem;">Delivery Location</h1>

                <% if(error !=null) { %>
                    <div class="message error"><%= error %>
                    </div>
                    <% } %>

                        <div
                            style="text-align: center; padding: 1.5rem; background: var(--bg-secondary); border-radius: 8px; margin-bottom: 2rem;">
                            <h2 style="color: var(--accent-gold); margin: 0;">Order Total</h2>
                            <p style="font-size: 2rem; color: var(--accent-cyan); margin: 0.5rem 0;">
                                Rs.<%= String.format("%.2f", total) %>
                            </p>
                        </div>

                        <form action="order" method="post">
                            <input type="hidden" name="total" value="<%= total %>">

                            <label for="location">Where shall we deliver your feast?</label>
                            <textarea id="location" name="location" rows="5"
                                placeholder="Enter your complete delivery address:&#10;&#10;House/Flat No, Building Name&#10;Street/Area&#10;Landmark&#10;City, Pincode"
                                required></textarea>

                            <div
                                style="margin-top: 1rem; padding: 1rem; background: rgba(157, 78, 221, 0.1); border-radius: 6px; border-left: 4px solid var(--accent-purple);">
                                <p style="margin: 0; font-size: 0.9rem; color: var(--text-secondary);">
                                    <strong>Tip:</strong> Provide detailed address for faster delivery. Our shadow
                                    riders will find you!
                                </p>
                            </div>

                            <div class="flex-center" style="margin-top: 2rem;">
                                <a href="cart" class="btn">Back to Cart</a>
                                <button type="submit" class="btn btn-primary">
                                    Confirm & Place Order
                                </button>
                            </div>
                        </form>
            </div>
        </div>

        <%@ include file="footer.jsp" %>