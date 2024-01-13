package com.calmdown.simpleGw.controller;

import com.calmdown.simpleGw.domain.Member;
import com.calmdown.simpleGw.dto.CertRequest;
import com.calmdown.simpleGw.service.MemberService;
import com.calmdown.simpleGw.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("v1/admin/payment")
@Controller
public class PaymentController {

    private final MemberService memberService;
    private final GatewayController gatewayController;

    @GetMapping("/{memberId}/pay")
    public String paymentForm(@PathVariable Long memberId, Model model) {
        Member member = memberService.findOne(memberId);

        PaymentForm form = new PaymentForm();
        form.setPhone(member.getPhone());
        form.setMobileCarrier(member.getMobileCarrier());
        model.addAttribute("form", form);

        return "payments/paymentForm";
    }

    @PostMapping("/{memberId}/pay")
    public String payment(@PathVariable Long memberId, @ModelAttribute("form") PaymentForm form) throws Exception {
        CertRequest request = CertRequest.builder()
                .phone(form.getPhone())
                .payAmount(form.getPayAmount())
                .mobileCarrier(form.getMobileCarrier())
                .build();
        gatewayController.cert(request);

        return "redirect:/v1/admin/members";
    }

    @GetMapping("/{memberId}/payList")
    public String paymentList(@PathVariable Long memberId, Model model) {
        Member member = memberService.findOne(memberId);

        PaymentForm form = new PaymentForm();
        form.setPhone(member.getPhone());
        form.setMobileCarrier(member.getMobileCarrier());
        model.addAttribute("form", form);

        return "payments/paymentList";
    }

}
