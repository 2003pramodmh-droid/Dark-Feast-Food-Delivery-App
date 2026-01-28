<%@ include file="header.jsp" %>

    <% Integer orderId=(Integer) session.getAttribute("lastOrderId"); Double orderTotal=(Double)
        session.getAttribute("lastOrderTotal"); %>

        <div style="text-align: center; padding: 3rem;">
            <div class="success-icon"></div>

            <h1 style="font-size: 3rem; margin-bottom: 1rem;">Order Confirmed!</h1>

            <div class="form-card" style="max-width: 600px; margin: 2rem auto;">
                <div style="margin: 2rem 0;">
                    <p style="font-size: 1.3rem; color: var(--accent-cyan); margin-bottom: 1rem;">
                        Your feast is being prepared by our mystical chefs!
                    </p>

                    <% if(orderId !=null) { %>
                        <div
                            style="padding: 1.5rem; background: var(--bg-secondary); border-radius: 8px; margin: 1.5rem 0;">
                            <p style="color: var(--text-secondary); margin-bottom: 0.5rem;">Order ID</p>
                            <p style="font-size: 2rem; color: var(--accent-gold); font-weight: bold; margin: 0;">
                                #<%= orderId %>
                            </p>
                        </div>
                        <% } %>

                            <% if(orderTotal !=null) { %>
                                <div
                                    style="padding: 1.5rem; background: var(--bg-secondary); border-radius: 8px; margin: 1.5rem 0;">
                                    <p style="color: var(--text-secondary); margin-bottom: 0.5rem;">Total Amount</p>
                                    <p
                                        style="font-size: 2rem; color: var(--accent-cyan); font-weight: bold; margin: 0;">
                                        Rs.<%= String.format("%.2f", orderTotal) %>
                                    </p>
                                </div>
                                <% } %>
                </div>

                <div
                    style="padding: 1.5rem; background: rgba(0, 245, 255, 0.1); border-radius: 8px; border: 2px solid var(--accent-cyan); margin: 2rem 0;">
                    <h3 style="color: var(--accent-cyan); margin-top: 0;">What happens next?</h3>
                    <ul style="text-align: left; color: var(--text-secondary); line-height: 2;">
                        <li>Our chefs are preparing your order with mystical ingredients</li>
                        <li>Shadow riders will deliver to your doorstep</li>
                        <li>Expected delivery: 30-45 minutes</li>
                        <li>Payment on delivery (Cash/Card/UPI)</li>
                    </ul>
                </div>

                <div class="flex-center" style="margin-top: 2rem; flex-direction: column; gap: 1rem;">
                    <a href="home" class="btn btn-primary" style="width: 100%; max-width: 300px;">
                        Return to Home
                    </a>
                    <p style="color: var(--text-muted); margin-top: 1rem;">
                        Track your order status: <strong style="color: var(--accent-gold);">Status...!</strong>
                    </p>
                </div>
            </div>

            <div
                style="margin-top: 3rem; padding: 2rem; background: var(--bg-card); border-radius: 12px; max-width: 600px; margin-left: auto; margin-right: auto;">
                <h3 style="color: var(--accent-purple);">Thank you for ordering!</h3>
                <p class="desc">
                    Your support keeps our magical kitchens running. Share your experience with fellow adventurers!
                </p>
            </div>
        </div>

        <%@ include file="footer.jsp" %>