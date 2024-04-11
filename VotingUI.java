package javas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class VotingUI {
    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JButton adminButton;
    private JButton voterButton;

    // Simulated storage for votes
    private Map<String, Integer> votesMap;

    public VotingUI() {
        frame = new JFrame("Voting System");
        frame.setSize(600, 300); // Set the frame size to 600x300 pixels

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        // Simulated storage for votes (candidateName, voteCount)
        votesMap = new HashMap<>();

        // Create admin panel
        JPanel adminPanel = new JPanel();
        adminPanel.setLayout(new BorderLayout());

        JTextArea adminTextArea = new JTextArea();
        adminTextArea.setEditable(false);
        JScrollPane adminScrollPane = new JScrollPane(adminTextArea);
        adminPanel.add(adminScrollPane, BorderLayout.CENTER);

        JButton refreshButton = new JButton("Refresh Votes");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Refresh votes display
                StringBuilder votesInfo = new StringBuilder("Votes Information:\n");
                for (Map.Entry<String, Integer> entry : votesMap.entrySet()) {
                    votesInfo.append(entry.getKey()).append(": ").append(entry.getValue()).append(" votes\n");
                }
                adminTextArea.setText(votesInfo.toString());
            }
        });
        adminPanel.add(refreshButton, BorderLayout.SOUTH);
        cardPanel.add(adminPanel, "ADMIN");

        // Create voter panel
        JPanel voterPanel = new JPanel();
        voterPanel.setLayout(new FlowLayout());
        voterPanel.add(new JLabel("Voter Panel"));

        JComboBox<String> candidateComboBox = new JComboBox<>(new String[]{"Candidate 1", "Candidate 2", "Candidate 3"});
        voterPanel.add(candidateComboBox);

        JButton voteButton = new JButton("Vote");
        voteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCandidate = (String) candidateComboBox.getSelectedItem();
                if (selectedCandidate != null) {
                    // Simulate voting
                    votesMap.put(selectedCandidate, votesMap.getOrDefault(selectedCandidate, 0) + 1);
                    JOptionPane.showMessageDialog(frame, "Vote cast for " + selectedCandidate);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a candidate before voting.");
                }
            }
        });
        voterPanel.add(voteButton);

        cardPanel.add(voterPanel, "VOTER");

        // Create buttons to switch between panels
        JPanel buttonPanel = new JPanel();
        adminButton = new JButton("Admin");
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "ADMIN");
            }
        });
        buttonPanel.add(adminButton);

        voterButton = new JButton("Voter");
        voterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "VOTER");
            }
        });
        buttonPanel.add(voterButton);

        // Add components to the frame
        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(cardPanel, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(VotingUI::new);
    }
}
