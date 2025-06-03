package longtp.example;

public class InsuranceClaim {
    private String claimId;
    private double amount;
    private String claimStatus;

    public InsuranceClaim(String id, double claimAmount) {
        if (claimAmount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        this.claimId = id;
        this.amount = claimAmount;
        this.claimStatus = "Pending";
    }

    public boolean processClaim(String statusUpdate) {
        if (claimStatus.equals("Pending")) {
            claimStatus = statusUpdate;
            return true;
        }
        return false;
    }

    public double calculatePayout() {
        if (claimStatus.equals("Approved")) {
            return amount * 0.85; // 85% payout
        }
        return 0;
    }

    public void updateClaimAmount(double newAmount) {
        if (newAmount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        amount = newAmount;
    }

    // Getter methods
    public String getClaimId() {
        return claimId;
    }

    public double getAmount() {
        return amount;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    @Override
    public String toString() {
        return "InsuranceClaim{" +
                "claimId='" + claimId + '\'' +
                ", amount=" + amount +
                ", claimStatus='" + claimStatus + '\'' +
                '}';
    }
}
